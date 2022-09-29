package com.plb.employeemgt.repository;

import com.plb.employeemgt.entity.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {
    List<Vinyl> findAllByAuthor_Name(String name);

    void deleteBySongName(String songName);

    Optional<Vinyl> findOneBySongName(String songName);
}
