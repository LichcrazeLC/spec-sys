package com.utm.specsys.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Method {
    @Id@GeneratedValue Long id;
    private String method;
    private String description;

    @OneToMany
    private List<QueryParameter> queryParameters;

    @OneToMany
    private List<URIParameter> uriParameters;

    @OneToMany
    private List<Header> headers;
    private Short bodyTypeReferenceIndex;

    @OneToMany
    private List<Trait> traits;

    @OneToMany
    private List<Response> responses;

    Method () {}

    public Method(String method, String description, List<QueryParameter> queryParameters, List<URIParameter> uriParameters, List<Header> headers, Short bodyTypeReferenceIndex, List<Trait> traits, List<Response> responses) {
        this.method = method;
        this.description = description;
        this.queryParameters = queryParameters;
        this.uriParameters = uriParameters;
        this.headers = headers;
        this.bodyTypeReferenceIndex = bodyTypeReferenceIndex;
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

    public Short getBodyTypeReferenceIndex() {
        return this.bodyTypeReferenceIndex;
    }

    public void setBodyTypeReferenceIndex(Short bodyTypeReferenceIndex) {
        this.bodyTypeReferenceIndex = bodyTypeReferenceIndex;
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
