package com.plb.employeemgt.service;

import com.plb.employeemgt.entity.Employee;
import com.plb.employeemgt.repository.EmployeeRepository;
import com.plb.employeemgt.repository.TaskRepository;
import com.plb.employeemgt.service.dto.EmployeeDTO;
import com.plb.employeemgt.service.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAll() {
        // Etape 1
        List<Employee> allEmployees = employeeRepository.findAll();
        // Instanciation de ma liste de DTO
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        // Boucle sur les entites tasks
        // Etape 2
        for (Employee employee : allEmployees) {
            // Creation de mes DTOs
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setHireDate(employee.getHireDate());
            employeeDTO.setSalary(employee.getSalary());
            employeeDTO.setCommissionPct(employee.getCommissionPct());

            // Ajout dans le tableau de sorti
            employeeDTOs.add(employeeDTO);
        }

        return employeeDTOs;
    }
}
