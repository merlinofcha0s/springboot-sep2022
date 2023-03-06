package com.plb.employeemgt.repository;

import com.plb.employeemgt.entity.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VinylRepository extends JpaRepository<Vinyl, UUID> {
    List<Vinyl> findAllByAuthor_Name(String name);

    void deleteBySongName(String songName);

    Optional<Vinyl> findOneBySongName(String songName);
}
