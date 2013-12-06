package rubtsov.documents.service;

import rubtsov.documents.data.model.dto.DepartmentDto;
import rubtsov.documents.data.model.entity.Department;

import java.util.List;
import java.util.Map;

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

    Department saveFromDto(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartmentsDtos();

    Map<Long, DepartmentDto> getDeptsAsMap();

    DepartmentDto getAsDto(Long id);

}
