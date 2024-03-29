package com.plb.employeemgt.service.dto;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EmployeeDTO {

    private UUID id;

    @NotNull
    private Instant hireDate;

    @NotNull
    private Long salary;

    @NotNull
    private Long commissionPct;

    private List<JobDTO> jobs = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public List<JobDTO> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobDTO> jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO employee = (EmployeeDTO) o;
        return Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(commissionPct, employee.commissionPct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hireDate, salary, commissionPct);
    }
}
