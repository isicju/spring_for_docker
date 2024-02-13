package demo.controller;

import demo.model.Employee;
import demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@AllArgsConstructor
public class InternalEmployeeController {

    private EmployeeRepository employeeRepository;

    @GetMapping("/internal/employees/{employeeId}")
    Employee getEmployee(@PathVariable("employeeId") Integer employeeId) {
        return employeeRepository.getEmployeeById(employeeId);
    }

}
