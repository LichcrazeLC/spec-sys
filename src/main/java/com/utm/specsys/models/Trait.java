package com.utm.specsys.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.*;

@Entity
@Table(name="traits")
public class Trait {
    @Id@GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name="spec_id", nullable=false)
    private Spec spec;

    private String name;

    @OneToMany(mappedBy="trait")
    private List<QueryParameter> queryParams;

    @ManyToOne
    @JoinColumn(name="method_id", nullable=false)
    private Trait method;

    @ManyToOne
    @JoinColumn(name="endpoint_id", nullable=false)
    private Endpoint endpoint;

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
