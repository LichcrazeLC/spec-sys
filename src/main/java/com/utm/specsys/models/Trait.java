package com.utm.specsys.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Trait {
    @Id@GeneratedValue Long id;
    private String name;

    @OneToMany
    private List<QueryParameter> queryParams;

    Trait () {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QueryParameter> getQueryParams() {
        return this.queryParams;
    }

    public void setQueryParams(List<QueryParameter> queryParams) {
        this.queryParams = queryParams;
    }

    public Trait(String name, List<QueryParameter> queryParams) {
        this.name = name;
        this.queryParams = queryParams;
    }

}
