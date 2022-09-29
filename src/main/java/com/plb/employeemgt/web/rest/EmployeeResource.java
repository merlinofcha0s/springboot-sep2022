package com.plb.employeemgt.web.rest;

import com.plb.employeemgt.service.EmployeeService;
import com.plb.employeemgt.service.dto.EmployeeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "Allow users to fetch all employees informations")
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

    @GetMapping("/get-by-salary/{salary}")
    public ResponseEntity<List<EmployeeDTO>> getAllBySalary(@PathVariable Long salary) {
        List<EmployeeDTO> allBySalary = employeeService.getBySalary(salary);

        if (allBySalary.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allBySalary);
        }
    }

    @GetMapping("/get-by-job-title/{jobTitle}")
    public ResponseEntity<List<EmployeeDTO>> getByJobTitle(@PathVariable String jobTitle) {
        List<EmployeeDTO> allByJobTitle = employeeService.getByJobTitle(jobTitle);

        if (allByJobTitle.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allByJobTitle);
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.save(employeeDTO));
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> update(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.save(employeeDTO));
    }

    @DeleteMapping("/by-salary/{salary}")
    public ResponseEntity<Void> delete(@PathVariable Long salary) {
        int nbRemoved = employeeService.deleteBySalary(salary);
        if (nbRemoved == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/by-id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException erdae) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Employee not found with id : " + id);
        }
    }
}
