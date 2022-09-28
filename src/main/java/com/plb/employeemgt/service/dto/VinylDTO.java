package com.plb.employeemgt.service.dto;

import com.plb.employeemgt.entity.Author;

import javax.persistence.*;
import java.time.LocalDate;

public class VinylDTO {

    private String songName;

    private LocalDate releaseDate;

    private String authorName;

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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
