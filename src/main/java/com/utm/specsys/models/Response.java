package com.utm.specsys.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Response {
    @Id@GeneratedValue Long id;
    private String httpStatus;
    private Short bodyExampleReferenceIndex;

    Response () {}

    public Response(String httpStatus, Short bodyExampleReferenceIndex) {
        this.httpStatus = httpStatus;
        this.bodyExampleReferenceIndex = bodyExampleReferenceIndex;
    }

    public String getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Short getBodyExampleReferenceIndex() {
        return this.bodyExampleReferenceIndex;
    }

    public void setBodyExampleReferenceIndex(Short bodyExampleReferenceIndex) {
        this.bodyExampleReferenceIndex = bodyExampleReferenceIndex;
    }


}
