package com.utm.specsys.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ResourceType {
    @Id@GeneratedValue Long id;
    private String name;
    private String description;

    @OneToMany
    private List<Method> methods;

    ResourceType () {}

    public ResourceType(String name, String description, List<Method> methods) {
        this.name = name;
        this.description = description;
        this.methods = methods;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Method> getMethods() {
        return this.methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

}
