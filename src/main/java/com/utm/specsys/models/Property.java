package com.utm.specsys.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="properties")
@DiscriminatorColumn(name = "type")
public class Property {
    @Id@GeneratedValue Long id;
    private String name;
    private Boolean required;
    
    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @ManyToOne
    private Property parentProperty;

    @ManyToOne
    @JoinColumn(name="data_type_id", nullable=true)
    private DataType dataType;

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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Property getParentProperty() {
        return this.parentProperty;
    }

    public void setParentProperty(Property property) {
        this.parentProperty = property;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

}
