package com.utm.specsys.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Spec {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @Column(name = "created_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updated;

    private String userId;

    Boolean isPublic;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="spec")
    @JsonIgnore
    private Set<File> files;

    public Spec() {

    }

    public Spec(Long id, String name, LocalDateTime created, LocalDateTime updated, String userId, Boolean isPublic, Set<File> files) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.userId = userId;
        this.isPublic = isPublic;
        this.files = files;
    }

    public Long getId() {
        return this.id;
    }

    public Boolean getIsPublic() {
        return this.isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
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

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Spec created(LocalDateTime created) {
        setCreated(created);
        return this;
    }

    public Spec updated(LocalDateTime updated) {
        setUpdated(updated);
        return this;
    }

    public Spec userId(String userId) {
        setUserId(userId);
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
        return Objects.equals(id, spec.id) && Objects.equals(name, spec.name) && Objects.equals(created, spec.created) && Objects.equals(updated, spec.updated) && Objects.equals(userId, spec.userId) && Objects.equals(files, spec.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, updated, userId, files);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", user='" + getUserId() + "'" +
            ", files='" + getFiles() + "'" +
            "}";
    }
    
  

}
