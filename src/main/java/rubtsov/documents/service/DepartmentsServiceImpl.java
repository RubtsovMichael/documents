package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.DepartmentDto;
import rubtsov.documents.data.model.entity.Department;
import rubtsov.documents.data.repository.DepartmentsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 03.10.13
 * Time: 15:55
 */
@Service
public class DepartmentsServiceImpl implements DepartmentsService {

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentsRepository.findAll();
    }

    @Override
    public Department load(Long id) {
        return departmentsRepository.findOne(id);
    }

    @Override
    public Department save(Department department) {
        return departmentsRepository.saveAndFlush(department);
    }

    @Override
    public Department saveFromDto(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            throw new IllegalArgumentException("Department dto required!");
        }

        if (departmentDto.getDepartmentId() == null) {
            throw new IllegalArgumentException("Department dto has null id!");
        }

        Department department;
        if (departmentDto.getDepartmentId().equals(Long.valueOf(-1L))) {
            department = new Department();
        } else {
            department = load(departmentDto.getDepartmentId());
        }

        department.setCode(departmentDto.getCode());
        department.setFullName(departmentDto.getFullName());
        department.setShortName(departmentDto.getShortName());

        return save(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartmentsDtos() {
        ArrayList<DepartmentDto> departmentDtos = new ArrayList<>();

        for (Department department : getAllDepartments()) {
            departmentDtos.add(new DepartmentDto(department));
        }

        return departmentDtos;
    }
}
