package com.plb.employeemgt.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "author")
    private Set<Vinyl> vinyls = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Vinyl> getVinyls() {
        return vinyls;
    }

    public void setVinyls(Set<Vinyl> vinyls) {
        this.vinyls = vinyls;
    }
}
