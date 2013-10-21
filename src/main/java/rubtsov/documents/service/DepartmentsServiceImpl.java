package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.Department;
import rubtsov.documents.data.repository.DepartmentsRepository;

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
}
