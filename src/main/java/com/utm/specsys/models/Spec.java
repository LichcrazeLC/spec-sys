package com.utm.specsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="specs")
public class Spec {

    private @Id @GeneratedValue Long id;
    private Long ownerId;
    private String dateCreated;
    private String lastUpdated;

    @OneToMany(mappedBy="spec")
    private List<ResourceType> resourceTypes;

    @OneToMany(mappedBy="spec")
    private List<Trait> traits;

    @OneToMany(mappedBy="spec")
    private List<Example> examples;

    @OneToMany(mappedBy="spec")
    private List<DataType> dataTypes;

    @OneToMany(mappedBy="spec")
    private List<Endpoint> endpoints;

    Spec() {}

    public Spec(Long ownerId, String dateCreated, String lastUpdated, List<ResourceType> resourceTypes, List<Trait> traits, List<Example> examples, List<DataType> dataTypes, List<Endpoint> endpoints) {
        this.ownerId = ownerId;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.resourceTypes = resourceTypes;
        this.traits = traits;
        this.examples = examples;
        this.dataTypes = dataTypes;
        this.endpoints = endpoints;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<ResourceType> getResourceTypes() {
        return this.resourceTypes;
    }

    public void setResourceTypes(List<ResourceType> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    public List<Trait> getTraits() {
        return this.traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public List<Example> getExamples() {
        return this.examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public List<DataType> getDataTypes() {
        return this.dataTypes;
    }

    public void setDataTypes(List<DataType> dataTypes) {
        this.dataTypes = dataTypes;
    }

    public List<Endpoint> getEndpoints() {
        return this.endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }
    

}
