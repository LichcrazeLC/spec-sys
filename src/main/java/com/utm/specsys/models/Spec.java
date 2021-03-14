package com.utm.specsys.models;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@JsonIgnoreProperties(value = { "user" })
public class Spec {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @Column(name = "created_datetime")
    @CreationTimestamp
    private Date created;

    @Column(name = "updated_datetime")
	@UpdateTimestamp
	private Date updated;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private User owner;

    @OneToMany(mappedBy="spec")
    private Set<File> files;

    public Spec() {

    }


    public Spec(Long id, String name, Date created, Date updated, User user, Set<File> files) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.owner = user;
        this.files = files;
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

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getUser() {
        return this.owner;
    }

    public void setUser(User user) {
        this.owner = user;
    }

    public Set<File> getFiles() {
        return this.files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public Spec id(Long id) {
        setId(id);
        return this;
    }

    public Spec name(String name) {
        setName(name);
        return this;
    }

    public Spec created(Date created) {
        setCreated(created);
        return this;
    }

    public Spec updated(Date updated) {
        setUpdated(updated);
        return this;
    }

    public Spec user(User user) {
        setUser(user);
        return this;
    }

    public Spec files(Set<File> files) {
        setFiles(files);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Spec)) {
            return false;
        }
        Spec spec = (Spec) o;
        return Objects.equals(id, spec.id) && Objects.equals(name, spec.name) && Objects.equals(created, spec.created) && Objects.equals(updated, spec.updated) && Objects.equals(owner, spec.owner) && Objects.equals(files, spec.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, updated, owner, files);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", user='" + getUser() + "'" +
            ", files='" + getFiles() + "'" +
            "}";
    }
    
  

}
