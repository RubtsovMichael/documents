package rubtsov.documents.web.dto;

import rubtsov.documents.data.model.Employee;
import rubtsov.documents.web.Utils.Conversions;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 04.11.13
 * Time: 16:34
 */
public class EmployeeDto implements EntityDto<Employee> {

    private Long employeeId;

    private DepartmentDto department;

    private PostDto post;

    private PersonDto person;

    private Calendar dateBegin;

    private Calendar dateEnd;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public Calendar getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Calendar dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
    }

    public EmployeeDto() {
    }

    public EmployeeDto(Employee employee) {
        loadFromEntity(employee);
    }

    @Override
    public void saveToEntity(Employee employee) {
    }

    @Override
    public void loadFromEntity(Employee employee) {
        employeeId = employee.getEmployeeId();
        department = new DepartmentDto(employee.getDepartment());
        post = new PostDto(employee.getPost());
        person = new PersonDto(employee.getPerson());
        dateBegin = Conversions.dateToViewCalendar(employee.getDateBegin());
        dateEnd = Conversions.dateToViewCalendar(employee.getDateEnd());
    }
}


