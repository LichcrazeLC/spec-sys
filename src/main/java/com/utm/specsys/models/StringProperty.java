package com.utm.specsys.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class StringProperty extends Property {
    private String example;

    StringProperty () {}

    public StringProperty(String name, Boolean required, String type, String example) {
        super(name, required, type);
        this.example = example;
    }
    
    public String getExample() {
        return this.example;
    }

    public void setExample(String example) {
        this.example = example;
    }


}
