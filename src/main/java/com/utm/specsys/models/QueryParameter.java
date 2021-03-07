package com.utm.specsys.models;

import javax.persistence.Entity;

@Entity
class QueryParameter extends Parameter {

    private String description;
    private Boolean required;

    QueryParameter() {}

    public QueryParameter(String name, String description, Boolean required, String type, String example) {
        super(name, type, example);
        this.description = description;
        this.required = required;

    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

}
