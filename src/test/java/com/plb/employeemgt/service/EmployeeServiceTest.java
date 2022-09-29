package com.plb.employeemgt.service;

import com.plb.employeemgt.EmployeemgtApplication;
import com.plb.employeemgt.entity.Employee;
import com.plb.employeemgt.repository.EmployeeRepository;
import com.plb.employeemgt.service.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = EmployeemgtApplication.class)
public class EmployeeServiceTest {

    public static final long DEFAULT_COMMISSION_PCT = 12L;
    public static final long DEFAULT_SALARY = 1400L;
    public static final Instant DEFAULT_HIRE_DATE = Instant.now();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeRepository mockedEmployeeRepository;

    @Autowired
    private JobService jobService;

    private EmployeeService employeeServiceWithMockedRepository;

    private Employee employee;

    public static Employee createEntity() {
        Employee employee = new Employee();
        employee.setCommissionPct(DEFAULT_COMMISSION_PCT);
        employee.setSalary(DEFAULT_SALARY);
        employee.setHireDate(DEFAULT_HIRE_DATE);
        return employee;
    }

    public static EmployeeDTO createDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setSalary(DEFAULT_SALARY);
        employeeDTO.setCommissionPct(DEFAULT_COMMISSION_PCT);
        employeeDTO.setHireDate(DEFAULT_HIRE_DATE);
        return employeeDTO;
    }

    @BeforeEach
    public void init() {
        employeeServiceWithMockedRepository
                = new EmployeeService(mockedEmployeeRepository, jobService);
        employee = createEntity();
        employeeRepository.deleteAll();
    }

    @Test
    @Transactional
    public void getAllShouldWork() {
        employeeRepository.save(employee);

        Employee secondEmployee = createEntity();
        employeeRepository.save(secondEmployee);

        List<EmployeeDTO> allEmployeeInDB = employeeService.getAll();

        assertThat(allEmployeeInDB.size()).isEqualTo(2);
        assertThat(allEmployeeInDB)
                .extracting(EmployeeDTO::getCommissionPct).contains(DEFAULT_COMMISSION_PCT);
    }

    @Test
    public void saveSuccess() {
        EmployeeDTO employeeDTO = employeeService.save(createDTO());

        Optional<Employee> employeeToVerify =
                employeeRepository.findById(employeeDTO.getId());

        assertThat(employeeToVerify).isPresent();
        assertThat(employeeToVerify.get().getSalary()).isEqualTo(DEFAULT_SALARY);
        assertThat(employeeToVerify.get().getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
        assertThat(employeeToVerify.get().getCommissionPct()).isEqualTo(DEFAULT_COMMISSION_PCT);
    }

    @Test
    public void saveSuccessWithMock() {
        Mockito.when(mockedEmployeeRepository.save(Mockito.any())).thenReturn(createEntity());

        EmployeeDTO employeeDTO = employeeServiceWithMockedRepository.save(createDTO());

        assertThat(employeeDTO).isNotNull();
        assertThat(employeeDTO.getSalary()).isEqualTo(DEFAULT_SALARY);
        assertThat(employeeDTO.getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
        assertThat(employeeDTO.getCommissionPct()).isEqualTo(DEFAULT_COMMISSION_PCT);
    }
}
