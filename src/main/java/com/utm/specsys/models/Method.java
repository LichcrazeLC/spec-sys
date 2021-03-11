package com.utm.specsys.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name="methods")
public class Method {
    @Id@GeneratedValue Long id;
    private String method;
    private String description;

    @OneToMany(mappedBy="method")
    private List<QueryParameter> queryParameters;

    @OneToMany(mappedBy="method")
    private List<URIParameter> uriParameters;

    @OneToMany(mappedBy="method")
    private List<Header> headers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "data_type_id", referencedColumnName = "id")
    private DataType dataType;

    @OneToMany(mappedBy="method")
    private List<Trait> traits;

    @OneToMany(mappedBy="method")
    private List<Response> responses;

    @ManyToOne
    @JoinColumn(name="resource_type_id", nullable=false)
    private ResourceType resourceType;

    Method () {}

    public Method(String method, String description, List<QueryParameter> queryParameters, List<URIParameter> uriParameters, List<Header> headers, DataType dataType, List<Trait> traits, List<Response> responses) {
        this.method = method;
        this.description = description;
        this.queryParameters = queryParameters;
        this.uriParameters = uriParameters;
        this.headers = headers;
        this.dataType = dataType;
        this.traits = traits;
        this.responses = responses;
    }


    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QueryParameter> getQueryParameters() {
        return this.queryParameters;
    }

    public void setQueryParameters(List<QueryParameter> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public List<URIParameter> getUriParameters() {
        return this.uriParameters;
    }

    public void setUriParameters(List<URIParameter> uriParameters) {
        this.uriParameters = uriParameters;
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public DataType getdataType() {
        return this.dataType;
    }

    public void setdataType(DataType dataType) {
        this.dataType = dataType;
    }

    public List<Trait> getTraits() {
        return this.traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public List<Response> getResponses() {
        return this.responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }


}
