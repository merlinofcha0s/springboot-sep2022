package com.plb.employeemgt.service;


import com.plb.employeemgt.entity.Author;
import com.plb.employeemgt.entity.Vinyl;
import com.plb.employeemgt.repository.AuthorRepository;
import com.plb.employeemgt.repository.VinylRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class VinylService {

    private final VinylRepository vinylRepository;
    private final AuthorRepository authorRepository;

    public VinylService(VinylRepository vinylRepository, AuthorRepository authorRepository) {
        this.vinylRepository = vinylRepository;
        this.authorRepository = authorRepository;
    }

    public void initData() {
        // Creation de mon entite
        Author author = new Author();
        author.setFirstname("titi");
        author.setBirthDate(LocalDate.now());
        author.setName("tata");
        // Sauvegarde dans la base de données
        authorRepository.save(author);

        // Creation de mon entite
        Vinyl vinyl = new Vinyl();
        vinyl.setReleaseDate(LocalDate.now());
        vinyl.setSongName("toto");
        vinyl.setAuthor(author);
        // Sauvegarde dans la base de données
        vinylRepository.save(vinyl);

        // Ajout du vinyl dans la liste d'author
        author.getVinyls().add(vinyl);

        author.setName("tata modified in transaction");

        System.out.println("Ok c'est sauvegardé");
    }
}
