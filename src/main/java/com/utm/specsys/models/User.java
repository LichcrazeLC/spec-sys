package com.utm.specsys.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;

    String firstName;

    String lastName;

    @OneToMany(mappedBy="owner")
    private Set<Spec> specs;

    public User() {

    }

    public User(Long id, String firstName, String lastName, Set<Spec> specs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specs = specs;
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

}
