package com.plb.employeemgt.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vinyl")
public class Vinyl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vinylSequenceGenerator")
    @SequenceGenerator(name = "vinylSequenceGenerator", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "song_name", nullable = false)
    private String songName;

    @NotNull
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @ManyToOne
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
