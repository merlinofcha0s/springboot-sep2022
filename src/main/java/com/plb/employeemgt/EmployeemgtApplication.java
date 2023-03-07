package com.plb.employeemgt;

import com.plb.employeemgt.entity.Employee;
import com.plb.employeemgt.entity.Job;
import com.plb.employeemgt.entity.Task;
import com.plb.employeemgt.repository.EmployeeRepository;
import com.plb.employeemgt.repository.JobRepository;
import com.plb.employeemgt.repository.TaskRepository;
import com.plb.employeemgt.service.VinylService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.time.Instant;

@SpringBootApplication
public class EmployeemgtApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EmployeemgtApplication.class);
        ConfigurableEnvironment environment = new StandardEnvironment();
        environment.setDefaultProfiles("dev");
        application.setEnvironment(environment);
        application.run(args);
    }

    @Bean
    public CommandLineRunner createData(VinylService vinylService,
                                        EmployeeRepository employeeRepository,
                                        JobRepository jobRepository,
                                        TaskRepository taskRepository) {
        return args -> {
            vinylService.initData();
            Employee employee1 = new Employee();
            employee1.setSalary(1600L);
            employee1.setCommissionPct(10L);
            employee1.setHireDate(Instant.now());

            Employee employee2 = new Employee();
            employee2.setSalary(1500L);
            employee2.setCommissionPct(10L);
            employee2.setHireDate(Instant.now());

            Employee employee3 = new Employee();
            employee3.setSalary(1700L);
            employee3.setCommissionPct(10L);
            employee3.setHireDate(Instant.now());

            employeeRepository.save(employee1);
            employeeRepository.save(employee2);
            employeeRepository.save(employee3);

            Job job = new Job();
            job.setJobTitle("Developer");
            job.setMaxSalary(4000L);
            job.setMinSalary(2000L);
            job.setEmployee(employee1);
            jobRepository.save(job);

            Task task = new Task();
            task.setTitle("Build spring application");
            task.setDescription("Implements new features");
            task.getJobs().add(job);
            taskRepository.save(task);

            task.setDescription("After change in transaction");

            job.getTasks().add(task);
        };
    }
}
