package com.utm.specsys.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.utm.specsys.exceptions.UserCreationFailedException;
import com.utm.specsys.models.User;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.authorization.client.resource.PolicyResource;
import org.keycloak.authorization.client.resource.ProtectedResource;
import org.keycloak.authorization.client.resource.ProtectionResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.UmaPermissionRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {

    private Keycloak keycloak;

    @Value("${keycloak.realm}")
    String realm;
    @Value("${keycloak.user-role}")
    String role;
    @Value("${keycloak.credentials.secret}")
    String clientSecret;
    @Value("${keycloak.resource}")
    String clientId;
    @Value("${keycloak.auth-server-url}")
    String serverUrl;

    public KeycloakService(@Value("${keycloak.auth-server-url}") String serverUrl,
            @Value("${keycloak.realm}") String realm, @Value("${keycloak.resource}") String clientId,
            @Value("${keycloak.credentials.secret}") String clientSecret,
            @Value("${keycloak-realm-admin.username}") String userName,
            @Value("${keycloak-realm-admin.password}") String password, @Value("${keycloak-user-role}") String role) {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).grantType(OAuth2Constants.PASSWORD).realm(realm)
                    .clientId(clientId).clientSecret(clientSecret).username(userName).password(password)
                    .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
        }
    }

    public User SaveUser(User newUser) {

        keycloak.tokenManager().getAccessToken();

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(newUser.getEmail());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        Response response = usersResource.create(user);

        if (response.getStatus() == 201) {

            String userId = CreatedResponseUtil.getCreatedId(response);
            newUser.setId(userId);

            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(newUser.getPassword());

            UserResource userResource = usersResource.get(userId);
            userResource.resetPassword(passwordCred);

            RoleRepresentation realmRoleUser = realmResource.roles().get(role).toRepresentation();

            // Assign realm role user to user
            userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));

            return newUser;
        } else if (response.getStatus() == 409) {
            throw new UserCreationFailedException();
        } else {
            throw new RuntimeException();
        }

    }

    public ResponseEntity<?> SignIn(User registeredUser) {

        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "password");

        Configuration configuration = new Configuration(serverUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        AccessTokenResponse response = authzClient.obtainAccessToken(registeredUser.getEmail(),
                registeredUser.getPassword());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> GetAllUsers() {

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        List<UserRepresentation> response = usersResource.list();

        return ResponseEntity.ok(response);
    }

    public UserRepresentation GetUserById(String id) {

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        UserRepresentation response = usersResource.get(id).toRepresentation();

        return response;

    }

    public void UpdatePassword(String newPassword, String id) {

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(newPassword);

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        usersResource.get(id).resetPassword(passwordCred);
    }

    public void CreateSpec(String specName, Long specId, UserRepresentation user, String authToken) {

        keycloak.tokenManager().getAccessToken();

        ResourceRepresentation resource = new ResourceRepresentation("Spec " + specName + " of user " + user.getEmail(),
                null, "/users/" + user.getId() + "/specs/" + specId, "urn:apibeaver-app:resources:specs");

        AuthzClient authzClient = AuthzClient.create();
        ProtectionResource protectionResource = authzClient.protection();
        ProtectedResource resourceClient = protectionResource.resource();

        resource.setOwner(user.getId());
        resource.setOwnerManagedAccess(true);
        resource = resourceClient.create(resource);

        UmaPermissionRepresentation ownerPermission = new UmaPermissionRepresentation();
        ownerPermission.setName("Permission to access spec " + specId + " for user " + user.getEmail());
        ownerPermission.setDescription("Owner is allowed to access");
        ownerPermission.addClient("resource-server");
        ownerPermission.addUser(user.getEmail());

        PolicyResource policyClient = AuthzClient.create().protection(authToken.substring(6))
                .policy(resource.getId());

        policyClient.create(ownerPermission);

    }

    public void CreateFile(String fileName, Long specId, UserRepresentation user, String authToken) {

        keycloak.tokenManager().getAccessToken();

        ResourceRepresentation resource = new ResourceRepresentation(
                "File " + fileName + " of user " + user.getEmail() + " for spec " + specId, null,
                "/users/" + user.getId() + "/specs/" + specId + "/files/" + fileName,
                "urn:apibeaver-app:resources:files");

        AuthzClient authzClient = AuthzClient.create();
        ProtectionResource protectionResource = authzClient.protection();
        ProtectedResource resourceClient = protectionResource.resource();

        resource.setOwner(user.getId());
        resource.setOwnerManagedAccess(true);
        resource = resourceClient.create(resource);

        UmaPermissionRepresentation ownerPermission = new UmaPermissionRepresentation();
        ownerPermission.setName("Permission to access file " + fileName + " for spec " + specId + " of user " + user.getEmail());
        ownerPermission.setDescription("Owner is allowed to access");
        ownerPermission.addClient("resource-server");
        ownerPermission.addUser(user.getEmail());

        PolicyResource policyClient = AuthzClient.create().protection(authToken.substring(6))
                .policy(resource.getId());

        policyClient.create(ownerPermission);
    }

    public void CreateSpecsResource(User user) {

        keycloak.tokenManager().getAccessToken();

        ResourceRepresentation resource = new ResourceRepresentation("Specs for " + user.getEmail(), null,
                "/users/" + user.getId() + "/specs", "urn:apibeaver-app:resources:specs");

        AuthzClient authzClient = AuthzClient.create();
        ProtectionResource protectionResource = authzClient.protection();
        ProtectedResource resourceClient = protectionResource.resource();

        resource.setOwner(user.getId());
        resource.setOwnerManagedAccess(true);
        resource = resourceClient.create(resource);

        // ADD PERMISSION TO THE RESOURCE

        UmaPermissionRepresentation ownerPermission = new UmaPermissionRepresentation();
        ownerPermission.setName("Permission to access own specs for " + user.getEmail());
        ownerPermission.setDescription("Owner is allowed to access");
        ownerPermission.addClient("resource-server");
        ownerPermission.addUser(user.getEmail());

        PolicyResource policyClient = AuthzClient.create().protection(user.getEmail(), user.getPassword())
                .policy(resource.getId());

        policyClient.create(ownerPermission);
    }

    public void CreateFilesResource(UserRepresentation user, Long specId, String authToken) {

        ResourceRepresentation resource = new ResourceRepresentation(
                "Files for " + user.getEmail() + " and spec " + specId, null,
                "/users/" + user.getId() + "/specs/" + specId + "/files", "urn:apibeaver-app:resources:files");

        AuthzClient authzClient = AuthzClient.create();
        ProtectionResource protectionResource = authzClient.protection();
        ProtectedResource resourceClient = protectionResource.resource();

        resource.setOwner(user.getId());
        resource.setOwnerManagedAccess(true);
        resource = resourceClient.create(resource);

        UmaPermissionRepresentation ownerPermission = new UmaPermissionRepresentation();
        ownerPermission.setName("Permission to access own files for " + user.getEmail() + " for spec " + specId);
        ownerPermission.setDescription("Owner is allowed to access");
        ownerPermission.addClient("resource-server");
        ownerPermission.addUser(user.getEmail());

        PolicyResource policyClient = AuthzClient.create().protection(authToken.substring(6))
                .policy(resource.getId());

        policyClient.create(ownerPermission);

    }

    public void CreateZipContentResource(UserRepresentation user, Long specId, String authToken) {

        ResourceRepresentation resource = new ResourceRepresentation(
                "ZipContent for " + user.getEmail() + " and spec " + specId, null,
                "/users/" + user.getId() + "/specs/" + specId + "/zipContent", "urn:apibeaver-app:resources:files");

        AuthzClient authzClient = AuthzClient.create();
        ProtectionResource protectionResource = authzClient.protection();
        ProtectedResource resourceClient = protectionResource.resource();

        resource.setOwner(user.getId());
        resource.setOwnerManagedAccess(true);
        resource = resourceClient.create(resource);

        UmaPermissionRepresentation ownerPermission = new UmaPermissionRepresentation();
        ownerPermission.setName("Permission to access zip content for " + user.getEmail() + " for spec " + specId);
        ownerPermission.setDescription("Owner is allowed to access");
        ownerPermission.addClient("resource-server");
        ownerPermission.addUser(user.getEmail());

        PolicyResource policyClient = AuthzClient.create().protection(authToken.substring(6))
                .policy(resource.getId());

        policyClient.create(ownerPermission);

    }

    // public void findPermissions()
    // {
    // AuthzClient authzClient = AuthzClient.create();
    // PolicyResource policyClient = authzClient.protection("will01@mail.ru",
    // "lovesilviu").policy("bdddcce4-cfbd-4d60-ae31-05a890e8262b");
    // List<UmaPermissionRepresentation> permList = policyClient.find("A Policy 10",
    // "", 0, 5);
    // UmaPermissionRepresentation ownerPermission = permList.get(0);
    // }

}
