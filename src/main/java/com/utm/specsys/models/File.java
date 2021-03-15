package com.utm.specsys.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class File {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String location;

    @Column(name = "created_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name="spec_id", nullable=false)
    @JsonIgnore
    private Spec spec;

    public File() {

    }

    public File(String name, String location, Spec spec) {
        this.name = name;
        this.location = location;
        this.spec = spec;
    }

    public File(Long id, String name, String location, LocalDateTime created, LocalDateTime updated, Spec spec) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.created = created;
        this.updated = updated;
        this.spec = spec;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Spec getSpec() {
        return this.spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public File id(Long id) {
        setId(id);
        return this;
    }

    public File name(String name) {
        setName(name);
        return this;
    }

    public File location(String location) {
        setLocation(location);
        return this;
    }

    public File created(LocalDateTime created) {
        setCreated(created);
        return this;
    }

    public File updated(LocalDateTime updated) {
        setUpdated(updated);
        return this;
    }

    public File spec(Spec spec) {
        setSpec(spec);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof File)) {
            return false;
        }
        File file = (File) o;
        return Objects.equals(id, file.id) && Objects.equals(name, file.name) && Objects.equals(location, file.location) && Objects.equals(created, file.created) && Objects.equals(updated, file.updated) && Objects.equals(spec, file.spec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, created, updated, spec);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", location='" + getLocation() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", spec='" + getSpec() + "'" +
            "}";
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
