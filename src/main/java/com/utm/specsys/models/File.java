package com.utm.specsys.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class File {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String location;

    @ManyToOne
    @JoinColumn(name="spec_id", nullable=false)
    private Spec spec;

    public File() {

    }

    public File(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
