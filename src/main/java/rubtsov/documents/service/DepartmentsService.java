package rubtsov.documents.service;

import rubtsov.documents.data.model.Department;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 03.10.13
 * Time: 15:53
 */
public interface DepartmentsService {

    List<Department> getAllDepartments();

    Department load(Long id);

    Department save(Department department);
}
