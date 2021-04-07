package com.utm.specsys.models;

import java.util.Objects;
import java.util.Set;

public class User {

    String id;

    String firstName;

    String lastName;

    String email;

    String password;

    private Set<Spec> specs;

    public User() {

    }

    public User(String id, String firstName, String lastName, Set<Spec> specs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specs = specs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(specs, user.specs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, specs);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", specs='" + getSpecs() + "'" +
            "}";
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Spec> getSpecs() {
        return this.specs;
    }

    public void setSpecs(Set<Spec> specs) {
        this.specs = specs;
    }


}
