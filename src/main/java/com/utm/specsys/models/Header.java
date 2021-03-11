package com.utm.specsys.models;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
class Header extends Parameter {

    private String description;
    private Boolean required;
    
    @ManyToOne
    @JoinColumn(name="method_id", nullable=false)
    private Method method;

    @ManyToOne
    @JoinColumn(name="endpoint_id", nullable=false)
    private Endpoint endpoint;

    Header() {}

    public Header(String name, String description, Boolean required, String type, String example) {
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
