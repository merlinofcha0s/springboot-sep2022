package com.plb.employeemgt.web.rest;

import com.plb.employeemgt.entity.Vinyl;
import com.plb.employeemgt.service.VinylService;
import com.plb.employeemgt.service.dto.VinylDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vinyls")
public class VinylResource {

    private final VinylService vinylService;

    public VinylResource(VinylService vinylService) {
        this.vinylService = vinylService;
    }

    @GetMapping
    public ResponseEntity<List<VinylDTO>> getAll() {
        // Etape 5 : Creation de ma resource (VinylResource)
        // Etape 6 : Quelle va etre la signature de ma methode ?
        // Etape 7 : Quelles sont les codes HTTP qui je dois renvoyer ?
        // Etape 8 : Ecriture du code de ma resource
        List<VinylDTO> allVinyls = vinylService.getAll();

        if (allVinyls.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allVinyls);
        }
    }

    @GetMapping("/by-author-name/{name}")
    public ResponseEntity<List<VinylDTO>> getByAuthorName(@PathVariable String name) {
        List<VinylDTO> vinylsByAuthorName = vinylService.getByAuthor(name);

        if (vinylsByAuthorName.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vinylsByAuthorName);
        }
    }

    @PostMapping
    public ResponseEntity<VinylDTO> save(@Valid @RequestBody VinylDTO vinylDTO) {
        return ResponseEntity.ok(vinylService.save(vinylDTO));
    }
}
