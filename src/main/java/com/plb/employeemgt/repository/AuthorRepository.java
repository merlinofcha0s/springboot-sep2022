package com.plb.employeemgt.repository;

import com.plb.employeemgt.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
