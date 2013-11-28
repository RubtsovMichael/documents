package rubtsov.documents.service;

import rubtsov.documents.data.model.dto.EmployeeDto;
import rubtsov.documents.data.model.entity.Employee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 28.11.13
 * Time: 18:13
 */
public interface EmployeesService {

    List<Employee> getAllEmployees();

    Employee load(Long id);

    Employee save(Employee employee);

    Employee saveFromDto(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployeesDtos();

    EmployeeDto getAsDto(Long id);


}
