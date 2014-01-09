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

    private Long departmentId;

    private PostDto post;

    private Long postId;

    private PersonDto person;

    private Long personId;

    private Date dateBegin;

    private Date dateEnd;

    private boolean fromDepartment = false;

    private boolean fromPerson = false;

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public boolean isFromDepartment() {
        return fromDepartment;
    }

    public void setFromDepartment(boolean fromDepartment) {
        this.fromDepartment = fromDepartment;
    }

    public boolean isFromPerson() {
        return fromPerson;
    }

    public void setFromPerson(boolean fromPerson) {
        this.fromPerson = fromPerson;
    }

    public EmployeeDto() {
    }

    public EmployeeDto(Employee employee) {
        employeeId = employee.getEmployeeId();
        department = new DepartmentDto(employee.getDepartment());
        departmentId = employee.getDepartment().getDepartmentId();
        post = new PostDto(employee.getPost());
        postId = employee.getPost().getPostId();
        person = new PersonDto(employee.getPerson());
        personId = employee.getPerson().getPersonId();
        dateBegin = employee.getDateBegin();
        dateEnd = employee.getDateEnd();
    }
}


