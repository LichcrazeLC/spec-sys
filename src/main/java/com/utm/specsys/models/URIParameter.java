package com.utm.specsys.models;

import javax.persistence.Entity;

@Entity
class URIParameter extends Parameter {

    URIParameter() {}

    public URIParameter(String name, String description, Boolean required, String type, String example) {
        super(name, type, example);
    }

}
