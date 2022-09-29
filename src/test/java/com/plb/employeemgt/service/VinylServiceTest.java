package com.plb.employeemgt.service;

import com.plb.employeemgt.EmployeemgtApplication;
import com.plb.employeemgt.entity.Author;
import com.plb.employeemgt.entity.Vinyl;
import com.plb.employeemgt.repository.AuthorRepository;
import com.plb.employeemgt.repository.VinylRepository;
import com.plb.employeemgt.service.dto.VinylDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = EmployeemgtApplication.class)
public class VinylServiceTest {

    public static final String DEFAULT_SONG_NAME = "SongName";
    public static final LocalDate DEFAULT_RELEASE_DATE = LocalDate.now();
    public static final String DEFAULT_AUTHOR_NAME = "name";
    public static final String DEFAULT_AUTHOR_FIRSTNAME = "firstname";
    public static final LocalDate DEFAULT_AUTHOR_BIRTHDATE = LocalDate.now();

    @Autowired
    private VinylService vinylService;

    @Autowired
    private VinylRepository vinylRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Mock
    private VinylRepository mockedVinylRepository;

    private VinylService vinylServiceWithMockedRepository;

    private VinylDTO vinylDTO;
    private Vinyl vinyl;
    private Author author;

    public static VinylDTO createDTO() {
        VinylDTO vinylDTO = new VinylDTO();
        vinylDTO.setSongName(DEFAULT_SONG_NAME);
        vinylDTO.setReleaseDate(DEFAULT_RELEASE_DATE);
        vinylDTO.setAuthorName(DEFAULT_AUTHOR_NAME);
        return vinylDTO;
    }

    public static Vinyl createEntity() {
        Vinyl vinyl = new Vinyl();
        vinyl.setSongName(DEFAULT_SONG_NAME);
        vinyl.setReleaseDate(DEFAULT_RELEASE_DATE);
        return vinyl;
    }

    public static Author createAuthorEntity() {
        Author author = new Author();
        author.setName(VinylServiceTest.DEFAULT_AUTHOR_NAME);
        author.setFirstname(DEFAULT_AUTHOR_FIRSTNAME);
        author.setBirthDate(DEFAULT_AUTHOR_BIRTHDATE);
        return author;
    }

    @BeforeEach
    public void init() {
        vinylServiceWithMockedRepository = new VinylService(mockedVinylRepository, authorRepository);
        vinylRepository.deleteAll();
        vinylDTO = createDTO();
        vinyl = createEntity();
        author = createAuthorEntity();
    }

    @Test
    public void getAllShouldWork() {
        // Etape 1 : Preparation des données
        authorRepository.save(author);
        vinyl.setAuthor(author);
        vinylRepository.save(vinyl);

        Vinyl secondVinyl = createEntity();
        secondVinyl.setAuthor(author);
        vinylRepository.save(secondVinyl);

        assertThat(vinylRepository.findAll().size()).isEqualTo(2);

        // Etape 2 : Execution du code que l'on veut tester
        List<VinylDTO> allVinylsInDB = vinylService.getAll();

        // Etape 3 : Vérification des données
        assertThat(allVinylsInDB.size()).isEqualTo(2);
        assertThat(allVinylsInDB)
                .extracting(VinylDTO::getSongName).contains(DEFAULT_SONG_NAME);
    }

    @Test
    public void saveSuccess() {
        VinylDTO vinylSaved = vinylService.save(createDTO());

        Optional<Vinyl> vinylToVerify =
                vinylRepository.findOneBySongName(vinylSaved.getSongName());

        assertThat(vinylToVerify).isPresent();
        assertThat(vinylToVerify.get().getSongName()).isEqualTo(DEFAULT_SONG_NAME);
        assertThat(vinylToVerify.get().getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
    }

    @Test
    public void saveSuccessWithMock() {
        Mockito.when(mockedVinylRepository.save(Mockito.any())).thenReturn(createEntity());

        VinylDTO vinylSaved = vinylServiceWithMockedRepository.save(createDTO());

        assertThat(vinylSaved).isNotNull();
        assertThat(vinylSaved.getSongName()).isEqualTo(DEFAULT_SONG_NAME);
        assertThat(vinylSaved.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
    }
}
