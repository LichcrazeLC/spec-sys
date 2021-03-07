package com.utm.specsys.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DataType {
    @Id@GeneratedValue Long id;
    private String name;
    private Short exampleReferenceIndex;
    private String type;
    @OneToMany
    private List<Property> properties;

    DataType () {}

    public DataType(String name, Short exampleReferenceIndex, String type, List<Property> properties) {
        this.name = name;
        this.exampleReferenceIndex = exampleReferenceIndex;
        this.type = type;
        this.properties = properties;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getExampleReferenceIndex() {
        return this.exampleReferenceIndex;
    }

    public void setExampleReferenceIndex(Short exampleReferenceIndex) {
        this.exampleReferenceIndex = exampleReferenceIndex;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }



}
