package com.plb.employeemgt.web.rest;

import com.plb.employeemgt.service.EmployeeService;
import com.plb.employeemgt.service.JobService;
import com.plb.employeemgt.service.dto.EmployeeDTO;
import com.plb.employeemgt.service.dto.JobDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobResource {

    private final JobService jobService;

    public JobResource(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAll() {
        // Etape 5 : Creation de ma resource (VinylResource)
        // Etape 6 : Quelle va etre la signature de ma methode ?
        // Etape 7 : Quelles sont les codes HTTP qui je dois renvoyer ?
        // Etape 8 : Ecriture du code de ma resource
        List<JobDTO> allJobs = jobService.getAll();

        if (allJobs.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allJobs);
        }
    }
}
