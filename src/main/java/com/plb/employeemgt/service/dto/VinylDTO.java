package com.plb.employeemgt.service.dto;

import com.plb.employeemgt.entity.Author;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class VinylDTO {

    @NotEmpty
//    @NotNull
    @Size(min = 6, message = "La songname doit avoir au moins 6 characters")
    private String songName;

    @NotNull(message = "Le vinyl doit avoir une release date")
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
