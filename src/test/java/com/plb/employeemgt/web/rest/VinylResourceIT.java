package com.plb.employeemgt.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plb.employeemgt.EmployeemgtApplication;
import com.plb.employeemgt.entity.Author;
import com.plb.employeemgt.entity.Vinyl;
import com.plb.employeemgt.repository.AuthorRepository;
import com.plb.employeemgt.repository.VinylRepository;
import com.plb.employeemgt.service.VinylServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasItems;

@SpringBootTest(classes = EmployeemgtApplication.class)
@AutoConfigureMockMvc
public class VinylResourceIT {

    @Autowired
    private VinylRepository vinylRepository;

    private Vinyl vinyl;

    @Autowired
    private MockMvc restVinylMockMVC;

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    public void init() {
        vinyl = VinylServiceTest.createEntity();
    }

    @Test
    public void getAllVinyls() throws Exception {
        Author author = VinylServiceTest.createAuthorEntity();
        authorRepository.save(author);
        vinyl.setAuthor(author);
        vinylRepository.save(vinyl);

        Vinyl secondVinyl = VinylServiceTest.createEntity();
        secondVinyl.setAuthor(author);
        vinylRepository.save(secondVinyl);

        restVinylMockMVC.perform(MockMvcRequestBuilders.get("/api/vinyls"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].songName")
                        .value(hasItems(vinyl.getSongName(), secondVinyl.getSongName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].releaseDate")
                        .value(hasItems(vinyl.getReleaseDate().toString(),
                                secondVinyl.getReleaseDate().toString())));
    }
}
