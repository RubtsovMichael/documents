package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.EmployeeDto;
import rubtsov.documents.data.model.entity.Employee;
import rubtsov.documents.data.repository.EmployeesRepository;

import java.util.ArrayList;
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

    @Autowired
    PersonsService personsService;

    @Autowired
    PostsService postsService;

    @Autowired
    DepartmentsService departmentsService;

    @Override
    public List<Employee> getAllEmployees() {
        return employeesRepository.findAll();
    }

    @Override
    public Employee load(Long id) {
        return employeesRepository.findOne(id);
    }

    @Override
    public Employee save(Employee user) {
        return employeesRepository.saveAndFlush(user);
    }

    @Override
    public Employee saveFromDto(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            throw new IllegalArgumentException("Employee dto required!");
        }

        if (employeeDto.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee dto has null id!");
        }

        Employee employee;
        if (employeeDto.getEmployeeId().equals(Long.valueOf(-1L))) {
            employee = new Employee();
        } else {
            employee = load(employeeDto.getEmployeeId());
            if (employee == null)
                throw new IllegalArgumentException("Employee with id [" + employeeDto.getEmployeeId() + "] is not found for update");
        }

        employee.setDateBegin(employeeDto.getDateBegin());
        employee.setDateEnd(employeeDto.getDateEnd());

        if (!employeeDto.getPersonId().equals(employeeDto.getPerson().getPersonId()))
            employee.setPerson(personsService.load(employeeDto.getPersonId()));

        if (!employeeDto.getPostId().equals(employeeDto.getPost().getPostId()))
            employee.setPost(postsService.load(employeeDto.getPostId()));

        if (!employeeDto.getDepartmentId().equals(employeeDto.getDepartment().getDepartmentId()))
            employee.setDepartment(departmentsService.load(employeeDto.getDepartmentId()));

        return save(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployeesDtos() {
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : getAllEmployees()) {
            employeeDtos.add(new EmployeeDto(employee));
        }

        return employeeDtos;
    }

    @Override
    public EmployeeDto getAsDto(Long id) {
        Employee employee = load(id);

        if (employee == null) {
            throw new IllegalArgumentException("Employee ID [" + id + "] is not found");
        }

        return new EmployeeDto(employee);
    }
}
