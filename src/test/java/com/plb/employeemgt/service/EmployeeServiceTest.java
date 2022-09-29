package com.plb.employeemgt.service;

import com.plb.employeemgt.EmployeemgtApplication;
import com.plb.employeemgt.entity.Employee;
import com.plb.employeemgt.repository.EmployeeRepository;
import com.plb.employeemgt.service.dto.EmployeeDTO;
import com.plb.employeemgt.service.dto.VinylDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = EmployeemgtApplication.class)
public class EmployeeServiceTest {

    public static final long DEFAULT_COMMISSION_PCT = 12L;
    public static final long DEFAULT_SALARY = 1400L;
    public static final Instant DEFAULT_HIRE_DATE = Instant.now();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    private Employee createEntity() {
        Employee employee = new Employee();
        employee.setCommissionPct(DEFAULT_COMMISSION_PCT);
        employee.setSalary(DEFAULT_SALARY);
        employee.setHireDate(DEFAULT_HIRE_DATE);
        return employee;
    }

    @BeforeEach
    public void init() {
        employee = createEntity();
    }

    @Test
    @Transactional
    public void getAllShouldWork() {
        employeeRepository.save(employee);

        Employee secondEmployee = createEntity();
        employeeRepository.save(secondEmployee);

        List<EmployeeDTO> allEmployeeInDB = employeeService.getAll();

        assertThat(allEmployeeInDB.size()).isEqualTo(2);
        assertThat(allEmployeeInDB)
                .extracting(EmployeeDTO::getCommissionPct).contains(DEFAULT_COMMISSION_PCT);
    }
}
