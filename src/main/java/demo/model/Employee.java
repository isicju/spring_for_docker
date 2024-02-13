package demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Employee  {
    private final Integer employeeId;
    private final String firstName;
    private final String lastName;
    private final Integer departmentId;
    private final String departmentName;
}
