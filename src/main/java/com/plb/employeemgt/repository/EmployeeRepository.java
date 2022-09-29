package com.plb.employeemgt.repository;

import com.plb.employeemgt.entity.Employee;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllBySalary(Long salary);

//    @Query("SELECT e FROM Employee e WHERE e.salary = 1500")
//    @NamedNativeQuery("")
    List<Employee> findAllByJobs_jobTitleContains(String jobTitle);

    int deleteBySalary(Long salary);
}
