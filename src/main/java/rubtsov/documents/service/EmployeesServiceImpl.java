package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.EmployeeDto;
import rubtsov.documents.data.model.entity.Employee;
import rubtsov.documents.data.repository.EmployeesRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 28.11.13
 * Time: 18:15
 */
@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    EmployeesRepository employeesRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee load(Long id) {
        return null;
    }

    @Override
    public Employee save(Employee user) {
        return null;
    }

    @Override
    public Employee saveFromDto(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public List<EmployeeDto> getAllEmployeesDtos() {
        return null;
    }

    @Override
    public EmployeeDto getAsDto(Long id) {
        return null;
    }
}
