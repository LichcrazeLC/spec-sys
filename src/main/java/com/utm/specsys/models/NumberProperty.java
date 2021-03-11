package com.utm.specsys.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("4")
public class NumberProperty extends Property {
    private Integer example;

    NumberProperty () {}

    public NumberProperty(String name, Boolean required, String type, Integer example) {
        super(name, required, type);
        this.example = example;
    }
    
    public Integer getExample() {
        return this.example;
    }

    public void setExample(Integer example) {
        this.example = example;
    }


}
