package demo.repository;

import demo.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor

public class EmployeeRepository {

    private JdbcTemplate jdbcTemplate;

    public Employee getEmployeeById(Integer employeeId) {
        String query = "SELECT * FROM employees emp JOIN departments dep ON emp.department_id  = dep.department_id where emp.employee_id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{employeeId}, (rs, rowNum) ->
                    Employee.builder()
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .employeeId(rs.getInt("employee_id"))
                            .departmentId(rs.getInt("department_id"))
                            .departmentName(rs.getString("department_name"))
                            .build());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
