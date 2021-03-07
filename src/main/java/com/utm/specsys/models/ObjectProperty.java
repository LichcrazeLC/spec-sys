package com.utm.specsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ObjectProperty extends Property {

    @OneToMany
    private List<Property> properties;

    ObjectProperty () {}

    public ObjectProperty(String name, Boolean required, String type, List<Property> properties) {
        super(name, required, type);
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
    

}
