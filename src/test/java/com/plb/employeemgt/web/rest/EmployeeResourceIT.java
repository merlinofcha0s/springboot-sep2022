package com.plb.employeemgt.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plb.employeemgt.EmployeemgtApplication;
import com.plb.employeemgt.entity.Employee;
import com.plb.employeemgt.repository.EmployeeRepository;
import com.plb.employeemgt.service.EmployeeServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmployeemgtApplication.class)
@AutoConfigureMockMvc
public class EmployeeResourceIT {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @Autowired
    private MockMvc restEmployeeMockMVC;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void init() {
        employee = EmployeeServiceTest.createEntity();
    }

    @Test
    public void getAllEmployees() throws Exception {
        employeeRepository.save(employee);

        Employee secondEmployee = EmployeeServiceTest.createEntity();
        employeeRepository.save(secondEmployee);

        restEmployeeMockMVC.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].salary")
                        .value(hasItems(employee.getSalary().intValue(),
                                secondEmployee.getSalary().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].hireDate")
                        .value(hasItems(employee.getHireDate().toString(),
                                secondEmployee.getHireDate().toString())));
    }


    @Test
    public void save() throws Exception {
        int databaseSizeCreate = employeeRepository.findAll().size();

        Employee secondEmployee = EmployeeServiceTest.createEntity();

        restEmployeeMockMVC.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(secondEmployee)))
                .andExpect(status().isOk());

        List<Employee> all = employeeRepository.findAll();
        assertThat(all.size()).isEqualTo(databaseSizeCreate + 1);
    }
}
