package com.utm.specsys.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@DiscriminatorValue("2")
public class ArrayProperty extends Property {

    @JoinColumn(name="data_type_id", nullable=false)
    private DataType dataType;

    ArrayProperty () {}

    public ArrayProperty(String name, Boolean required, String type, DataType dataType) {
        super(name, required, type);
        this.dataType = dataType;
    }
    
    public DataType getItemsReferenceIndex() {
        return this.dataType;
    }

    public void setItemsReferenceIndex(DataType dataType) {
        this.dataType = dataType;
    }

}
