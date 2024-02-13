package demo.service;

import com.google.gson.Gson;
import demo.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@AllArgsConstructor
public class RedisCacheService {

    public Jedis jedis;

    public Employee getEmployee(Integer employeeId) {
        String employeeJson = jedis.get(String.valueOf(employeeId));
        return employeeJson == null ? null : new Gson().fromJson(employeeJson, Employee.class);
    }

    public void saveInCache(Employee employee) {
        jedis.set(String.valueOf(employee.getEmployeeId()), (new Gson().toJson(employee)));
    }

}
