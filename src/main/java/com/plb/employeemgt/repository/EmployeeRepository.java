package com.plb.employeemgt.repository;

import com.plb.employeemgt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findAllBySalary(Long salary);

    //    @Query("SELECT e FROM Employee e WHERE e.salary = 1500")
//    @NamedNativeQuery("")
    List<Employee> findAllByJobs_jobTitleContains(String jobTitle);

    int deleteBySalary(Long salary);
}
