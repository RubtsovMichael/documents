package rubtsov.documents.data.model.dto;

import rubtsov.documents.data.model.entity.Employee;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 04.11.13
 * Time: 16:34
 */
public class EmployeeDto {

    private Long employeeId;

    private DepartmentDto department;

    private PostDto post;

    private PersonDto person;

    private Date dateBegin;

    private Date dateEnd;

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

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public EmployeeDto() {
    }

    public EmployeeDto(Employee employee) {
        employeeId = employee.getEmployeeId();
        department = new DepartmentDto(employee.getDepartment());
        post = new PostDto(employee.getPost());
        person = new PersonDto(employee.getPerson());
        dateBegin = employee.getDateBegin();
        dateEnd = employee.getDateEnd();
    }
}


