package com.plb.employeemgt.repository;

import com.plb.employeemgt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
