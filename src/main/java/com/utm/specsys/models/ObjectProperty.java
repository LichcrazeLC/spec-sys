package com.utm.specsys.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("object")
public class ObjectProperty extends Property {

    @OneToMany(mappedBy="parentProperty", cascade = CascadeType.ALL)
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
