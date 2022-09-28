package com.plb.employeemgt.service;

import com.plb.employeemgt.entity.Job;
import com.plb.employeemgt.entity.Task;
import com.plb.employeemgt.repository.JobRepository;
import com.plb.employeemgt.service.dto.JobDTO;
import com.plb.employeemgt.service.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDTO> getAll() {
        // Etape 1
        List<Job> allJobs = jobRepository.findAll();
        return mapJobs(new HashSet<>(allJobs));
    }

    public List<JobDTO> mapJobs(Set<Job> jobs) {
        List<JobDTO> jobDTOS = new ArrayList<>();
        for (Job job : jobs) {
            // Creation de mes DTOs
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobTitle(job.getJobTitle());
            jobDTO.setMinSalary(job.getMinSalary());
            jobDTO.setMaxSalary(job.getMaxSalary());
            jobDTO.setMeanSalary(78L);
            // Ajout dans le tableau de sorti
            jobDTOS.add(jobDTO);
        }
        return jobDTOS;
    }
}
