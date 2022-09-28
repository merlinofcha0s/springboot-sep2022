package com.plb.employeemgt.repository;

import com.plb.employeemgt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllBySalary(Long salary);
    List<Employee> findAllByJobs_jobTitleContains(String jobTitle);
}
