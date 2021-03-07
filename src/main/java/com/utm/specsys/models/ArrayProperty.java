package com.utm.specsys.models;

import javax.persistence.Entity;

@Entity
public class ArrayProperty extends Property {
    private Short itemsReferenceIndex;

    ArrayProperty () {}

    public ArrayProperty(String name, Boolean required, String type, Short itemsReferenceIndex) {
        super(name, required, type);
        this.itemsReferenceIndex = itemsReferenceIndex;
    }
    
    public Short getItemsReferenceIndex() {
        return this.itemsReferenceIndex;
    }

    public void setItemsReferenceIndex(Short itemsReferenceIndex) {
        this.itemsReferenceIndex = itemsReferenceIndex;
    }

}
