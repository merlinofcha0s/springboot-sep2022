package com.plb.employeemgt.service;

import com.plb.employeemgt.EmployeemgtApplication;
import com.plb.employeemgt.entity.Employee;
import com.plb.employeemgt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest(classes = EmployeemgtApplication.class)
public class EmployeeServiceTest {

    public static final long DEFAULT_COMMISSION_PCT = 12L;
    public static final long DEFAULT_SALARY = 1400L;
    public static final Instant DEFAULT_HIRE_DATE = Instant.now();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee createEntity() {
        Employee employee = new Employee();
        employee.setCommissionPct(DEFAULT_COMMISSION_PCT);
        employee.setSalary(DEFAULT_SALARY);
        employee.setHireDate(DEFAULT_HIRE_DATE);
        return employee;
    }



}
