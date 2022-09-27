package com.plb.employeemgt;

import com.plb.employeemgt.entity.Author;
import com.plb.employeemgt.entity.Vinyl;
import com.plb.employeemgt.repository.AuthorRepository;
import com.plb.employeemgt.repository.VinylRepository;
import com.plb.employeemgt.service.VinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.time.LocalDate;

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
    public CommandLineRunner createData(VinylService vinylService) {
        return args -> {
            vinylService.initData();
        };
    }
}
