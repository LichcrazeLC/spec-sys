package com.utm.specsys.models;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("1")
public class ObjectProperty extends Property {

    @OneToMany(mappedBy="property")
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
