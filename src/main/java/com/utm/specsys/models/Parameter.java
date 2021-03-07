package com.utm.specsys.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Parameter {

    @Id@GeneratedValue Long id;
    private String name;
    //private String description;
    //private Boolean required;
    private String type;
    private String example;
    
    Parameter() {}

    public Parameter(String name, String type, String example) {
        this.name = name;
        this.type = type;
        this.example = example;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExample() {
        return this.example;
    }

    public void setExample(String example) {
        this.example = example;
    }


}
