package com.utm.specsys.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="responses")
public class Response {
    @Id@GeneratedValue Long id;
    private String httpStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "example_id", referencedColumnName = "id")
    private Example example;

    @ManyToOne
    @JoinColumn(name="method_id", nullable=false)
    private Trait method;

    @ManyToOne
    @JoinColumn(name="endpoint_id", nullable=false)
    private Endpoint endpoint;

    Response () {}

    public Response(String httpStatus, Example example) {
        this.httpStatus = httpStatus;
        this.example = example;
    }

    public String getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Example getExample() {
        return this.example;
    }

    public void setExample(Example example) {
        this.example = example;
    }


}
