package com.plb.employeemgt.service.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Job.
 */
public class JobDTO {

    private String jobTitle;

    private Long minSalary;

    private Long maxSalary;

    private Long meanSalary;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Long getMeanSalary() {
        return meanSalary;
    }

    public void setMeanSalary(Long meanSalary) {
        this.meanSalary = meanSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobDTO jobDTO = (JobDTO) o;
        return Objects.equals(jobTitle, jobDTO.jobTitle) &&
                Objects.equals(minSalary, jobDTO.minSalary) &&
                Objects.equals(maxSalary, jobDTO.maxSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobTitle, minSalary, maxSalary);
    }

    @Override
    public String toString() {
        return "Job{" +
            "jobTitle='" + getJobTitle() + "'" +
            ", minSalary=" + getMinSalary() +
            ", maxSalary=" + getMaxSalary() +
            "}";
    }
}
