package com.plb.employeemgt.service;


import com.plb.employeemgt.entity.Author;
import com.plb.employeemgt.entity.Vinyl;
import com.plb.employeemgt.repository.AuthorRepository;
import com.plb.employeemgt.repository.VinylRepository;
import com.plb.employeemgt.service.dto.VinylDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VinylService {

    private final VinylRepository vinylRepository;
    private final AuthorRepository authorRepository;

    public VinylService(VinylRepository vinylRepository, AuthorRepository authorRepository) {
        this.vinylRepository = vinylRepository;
        this.authorRepository = authorRepository;
    }


    // Methode pour recuperer tous les vinyls
    public List<VinylDTO> getAll() {
        // Qu'est ce que je dois faire ?
        // Etape 1 : Je dois aller chercher mes vinyls dans ma base de données
        // Etape 2 : Je transforme mes entités vinyls en DTO
        // Etape 3 : Je renvoi ma liste de DTO

        // Etape 1
        List<Vinyl> allVinyls = vinylRepository.findAll();
        return mapVinyls(allVinyls);
    }

    private List<VinylDTO> mapVinyls(List<Vinyl> allVinyls) {
        List<VinylDTO> vinylDTOs = new ArrayList<>();
        for (Vinyl vinyl : allVinyls) {
            // Creation de mes DTOs
            VinylDTO vinylDTO = new VinylDTO();
            vinylDTO.setSongName(vinyl.getSongName());
            vinylDTO.setReleaseDate(vinyl.getReleaseDate());
            vinylDTO.setAuthorName(vinyl.getAuthor().getName());
            // Ajout dans le tableau de sorti
            vinylDTOs.add(vinylDTO);
        }
        return vinylDTOs;
    }

    // Recuperer les vinyls par auteur
    public List<VinylDTO> getByAuthor(String authorName) {
        List<Vinyl> vinylsByAuthor = vinylRepository.findAllByAuthor_Name(authorName);
        return mapVinyls(vinylsByAuthor);
    }

    public VinylDTO save(VinylDTO vinylDTO) {
        Vinyl vinylToSave = mapDTOToEntity(vinylDTO);
        Vinyl vinylSaved = vinylRepository.save(vinylToSave);
        return mapEntityToDTO(vinylSaved);
    }

    @Transactional
    public void delete(String songName) {
        vinylRepository.deleteBySongName(songName);
    }

    private Vinyl mapDTOToEntity(VinylDTO vinylDTO) {
        Vinyl vinyl = new Vinyl();
        vinyl.setSongName(vinylDTO.getSongName());
        vinyl.setReleaseDate(vinylDTO.getReleaseDate());
        return vinyl;
    }

    private VinylDTO mapEntityToDTO(Vinyl vinyl) {
        VinylDTO vinylDTO = new VinylDTO();
        vinylDTO.setSongName(vinyl.getSongName());
        vinylDTO.setReleaseDate(vinyl.getReleaseDate());
//        vinylDTO.setAuthorName(vinyl.getAuthor().getName());
        return vinylDTO;
    }

    // Supprimer un vinyl

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

        Vinyl vinyl1 = new Vinyl();
        vinyl1.setReleaseDate(LocalDate.now());
        vinyl1.setSongName("tata");
        vinyl1.setAuthor(author);
        vinylRepository.save(vinyl1);
        // Ajout du vinyl dans la liste d'author
        author.getVinyls().add(vinyl);

        author.setName("tata modified in transaction");
    }
}
