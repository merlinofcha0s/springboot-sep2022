package com.plb.employeemgt.service;

import com.plb.employeemgt.entity.Job;
import com.plb.employeemgt.entity.Task;
import com.plb.employeemgt.repository.JobRepository;
import com.plb.employeemgt.service.dto.JobDTO;
import com.plb.employeemgt.service.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDTO> getAll() {
        // Etape 1
        List<Job> allJobs = jobRepository.findAll();
        // Instanciation de ma liste de DTO
        List<JobDTO> jobDTOS = new ArrayList<>();
        // Boucle sur les entites tasks
        // Etape 2
        for (Job job : allJobs) {
            // Creation de mes DTOs
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobTitle(job.getJobTitle());
            jobDTO.setMinSalary(job.getMinSalary());
            jobDTO.setMaxSalary(job.getMaxSalary());
            // Ajout dans le tableau de sorti
            jobDTOS.add(jobDTO);
        }
        return jobDTOS;
    }
}
