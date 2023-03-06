package com.plb.employeemgt.service;

import com.plb.employeemgt.entity.Employee;
import com.plb.employeemgt.repository.EmployeeRepository;
import com.plb.employeemgt.service.dto.EmployeeDTO;
import com.plb.employeemgt.service.dto.JobDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobService jobService;

    public EmployeeService(EmployeeRepository employeeRepository, JobService jobService) {
        this.employeeRepository = employeeRepository;
        this.jobService = jobService;
    }

    public List<EmployeeDTO> getAll() {
        // Etape 1
        List<Employee> allEmployees = employeeRepository.findAll();
        return mapEmployees(allEmployees);
    }

    private List<EmployeeDTO> mapEmployees(List<Employee> allEmployees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        // Boucle sur les entites tasks
        // Etape 2
        for (Employee employee : allEmployees) {
            // Creation de mes DTOs
            EmployeeDTO employeeDTO = mapEntityToDTO(employee);
            List<JobDTO> jobDTOS = jobService.mapJobs(employee.getJobs());
            employeeDTO.setJobs(jobDTOS);

            // Ajout dans le tableau de sorti
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    public List<EmployeeDTO> getBySalary(Long salary) {
        List<Employee> allBySalary = employeeRepository.findAllBySalary(salary);
        return mapEmployees(allBySalary);
    }

    public List<EmployeeDTO> getByJobTitle(String jobTitle) {
        List<Employee> allByJobTitle = employeeRepository.findAllByJobs_jobTitleContains(jobTitle);
        return mapEmployees(allByJobTitle);
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employeeToSave = mapDTOToEntity(employeeDTO);
        Employee employeeSaved = employeeRepository.save(employeeToSave);
        return mapEntityToDTO(employeeSaved);
    }

    private EmployeeDTO mapEntityToDTO(Employee employeeSaved) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeSaved.getId());
        employeeDTO.setSalary(employeeSaved.getSalary());
        employeeDTO.setCommissionPct(employeeSaved.getCommissionPct());
        employeeDTO.setHireDate(employeeSaved.getHireDate());
        return employeeDTO;
    }

    private Employee mapDTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setCommissionPct(employeeDTO.getCommissionPct());
        employee.setSalary(employeeDTO.getSalary());
        employee.setHireDate(employeeDTO.getHireDate());
        return employee;
    }

    @Transactional
    public int deleteBySalary(Long salary) {
        return employeeRepository.deleteBySalary(salary);
    }

    @Transactional
    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);
    }
}
