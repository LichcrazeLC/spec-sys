package com.utm.specsys.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="endpoints")
public class Endpoint {
    @Id@GeneratedValue Long id;
    private String path;
    private String method;

    @ManyToOne
    @JoinColumn(name="spec_id", nullable=false)
    private Spec spec;

    @OneToMany(mappedBy="endpoint")
    private List<QueryParameter> queryParameters;
    @OneToMany(mappedBy="endpoint")
    private List<URIParameter> uriParameters;
    @OneToMany(mappedBy="endpoint")
    private List<Header> headers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "data_type_id", referencedColumnName = "id")
    private DataType dataType;
    
    @OneToMany(mappedBy="endpoint")
    private List<Trait> traits;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "example_id", referencedColumnName = "id")
    private Example example;

    @OneToMany(mappedBy="endpoint")
    private List<Response> responses;

    Endpoint () {}


    public Endpoint(String path, String method, List<QueryParameter> queryParameters, List<URIParameter> uriParameters, List<Header> headers, DataType dataType, List<Trait> traits, Example example, List<Response> responses) {
        this.path = path;
        this.method = method;
        this.queryParameters = queryParameters;
        this.uriParameters = uriParameters;
        this.headers = headers;
        this.dataType = dataType;
        this.traits = traits;
        this.example = example;
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

    public DataType getdataType() {
        return this.dataType;
    }

    public void setdataType(DataType dataType) {
        this.dataType = dataType;
    }

    public List<Trait> getTraits() {
        return this.traits;
    }

    public void setTraitsReferenceIndexes(List<Trait> traits) {
        this.traits = traits;
    }

    public Example getexample() {
        return this.example;
    }

    public void setexample(Example example) {
        this.example = example;
    }

    public List<Response> getResponses() {
        return this.responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
    
}
