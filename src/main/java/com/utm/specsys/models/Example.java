package com.utm.specsys.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="examples")
public class Example {
    @Id@GeneratedValue Long id;
    
    @ManyToOne
    @JoinColumn(name="spec_id", nullable=false)
    private Spec spec;

    @OneToOne(mappedBy = "example")
    private Endpoint endpoint;

    @OneToOne(mappedBy = "example")
    private Response response;

    private String name;
    private String mediaType;
    private String body;

    Example () {}

    public Example(String name, String mediaType, String body) {
        this.name = name;
        this.mediaType = mediaType;
        this.body = body;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
