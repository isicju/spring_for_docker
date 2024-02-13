package demo.controller;

import demo.model.Employee;
import demo.service.EmployeeRemoteService;
import demo.service.RedisCacheService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@AllArgsConstructor
public class EmployeeMainController {

    private RedisCacheService redisCacheService;
    private EmployeeRemoteService remoteService;

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Integer employeeId) {
        Employee employee = redisCacheService.getEmployee(employeeId);
        if (employee == null) {
            employee = remoteService.getEmployee(employeeId);
            if (employee != null) {
                redisCacheService.saveInCache(employee);
            }
        }
        return employee;
    }


}
