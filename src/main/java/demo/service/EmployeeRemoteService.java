package demo.service;

import com.google.gson.Gson;
import demo.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeRemoteService {

    private RestTemplate restTemplate;

    public Employee getEmployee(Integer employeeId){
        return (new Gson().fromJson(restTemplate.getForObject("/" + employeeId, String.class), Employee.class));
    }

}
