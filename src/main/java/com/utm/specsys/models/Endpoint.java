package com.utm.specsys.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Endpoint {
    @Id@GeneratedValue Long id;
    private String path;
    private String method;
    @OneToMany
    private List<QueryParameter> queryParameters;
    @OneToMany
    private List<URIParameter> uriParameters;
    @OneToMany
    private List<Header> headers;
    private Short typeReferenceIndex;
    @OneToMany
    private List<Trait> traitsReferenceIndexes;
    private Short bodyReferenceIndex;
    @OneToMany
    private List<Response> responses;

    Endpoint () {}


    public Endpoint(String path, String method, List<QueryParameter> queryParameters, List<URIParameter> uriParameters, List<Header> headers, Short typeReferenceIndex, List<Trait> traitsReferenceIndexes, Short bodyReferenceIndex, List<Response> responses) {
        this.path = path;
        this.method = method;
        this.queryParameters = queryParameters;
        this.uriParameters = uriParameters;
        this.headers = headers;
        this.typeReferenceIndex = typeReferenceIndex;
        this.traitsReferenceIndexes = traitsReferenceIndexes;
        this.bodyReferenceIndex = bodyReferenceIndex;
        this.responses = responses;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public Short getTypeReferenceIndex() {
        return this.typeReferenceIndex;
    }

    public void setTypeReferenceIndex(Short typeReferenceIndex) {
        this.typeReferenceIndex = typeReferenceIndex;
    }

    public List<Trait> getTraitsReferenceIndexes() {
        return this.traitsReferenceIndexes;
    }

    public void setTraitsReferenceIndexes(List<Trait> traitsReferenceIndexes) {
        this.traitsReferenceIndexes = traitsReferenceIndexes;
    }

    public Short getBodyReferenceIndex() {
        return this.bodyReferenceIndex;
    }

    public void setBodyReferenceIndex(Short bodyReferenceIndex) {
        this.bodyReferenceIndex = bodyReferenceIndex;
    }

    public List<Response> getResponses() {
        return this.responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
    
}
