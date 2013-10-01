package rubtsov.documents.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rubtsov.documents.data.model.Employee;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 27.09.13
 * Time: 17:45
 */
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {
}
