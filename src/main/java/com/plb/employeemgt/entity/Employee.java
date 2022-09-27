package com.plb.employeemgt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSequenceGenerator")
    @SequenceGenerator(name = "employeeSequenceGenerator", allocationSize = 1)
    private Long id;

    @Column(name = "hire_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Instant hireDate;

    @Column(name = "salary", nullable = false)
    private Long salary;

    @Column(name = "commissionPCT")
    private Long commissionPct;

//    @OneToMany(mappedBy = "employee")
//    @JsonIgnoreProperties("employee")
//    private Set<Job> jobs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getHireDate() {
        return hireDate;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Long commissionPct) {
        this.commissionPct = commissionPct;
    }

//    public Set<Job> getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(Set<Job> jobs) {
//        this.jobs = jobs;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(commissionPct, employee.commissionPct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hireDate, salary, commissionPct);
    }
}
