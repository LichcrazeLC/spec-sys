package com.utm.specsys.models;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
class URIParameter extends Parameter {

    @ManyToOne
    @JoinColumn(name="method_id", nullable=false)
    private Method method;

    @ManyToOne
    @JoinColumn(name="endpoint_id", nullable=false)
    private Endpoint endpoint;
    
    URIParameter() {}

    public URIParameter(String name, String description, Boolean required, String type, String example) {
        super(name, type, example);
    }

}
