package com.utm.specsys.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Property {
    @Id@GeneratedValue Long id;
    private String name;
    private Boolean required;
    private String type;

    Property () {}

    public Property(String name, Boolean required, String type) {
        this.name = name;
        this.required = required;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isRequired() {
        return this.required;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
