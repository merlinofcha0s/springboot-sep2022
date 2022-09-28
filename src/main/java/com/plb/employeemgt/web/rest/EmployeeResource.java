package com.plb.employeemgt.web.rest;

import com.plb.employeemgt.entity.Employee;
import com.plb.employeemgt.service.EmployeeService;
import com.plb.employeemgt.service.VinylService;
import com.plb.employeemgt.service.dto.EmployeeDTO;
import com.plb.employeemgt.service.dto.VinylDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeResource {

    private EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        // Etape 5 : Creation de ma resource (VinylResource)
        // Etape 6 : Quelle va etre la signature de ma methode ?
        // Etape 7 : Quelles sont les codes HTTP qui je dois renvoyer ?
        // Etape 8 : Ecriture du code de ma resource
        List<EmployeeDTO> allEmployees = employeeService.getAll();

        if (allEmployees.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allEmployees);
        }
    }
}
