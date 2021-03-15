package com.utm.specsys.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;

    String firstName;

    String lastName;

    @Column(name = "created_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    @JsonIgnore
    private Set<Spec> specs;

    public User() {

    }

    public User(Long id, String firstName, String lastName, Set<Spec> specs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specs = specs;
    }

    public User(Long id, String firstName, String lastName, LocalDateTime created, LocalDateTime updated, Set<Spec> specs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
        this.updated = updated;
        this.specs = specs;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public User lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public User created(LocalDateTime created) {
        setCreated(created);
        return this;
    }

    public User updated(LocalDateTime updated) {
        setUpdated(updated);
        return this;
    }

    public User specs(Set<Spec> specs) {
        setSpecs(specs);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(created, user.created) && Objects.equals(updated, user.updated) && Objects.equals(specs, user.specs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, created, updated, specs);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", specs='" + getSpecs() + "'" +
            "}";
    }
 

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }


}
