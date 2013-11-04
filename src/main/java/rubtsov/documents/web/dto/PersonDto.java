package rubtsov.documents.web.dto;

import rubtsov.documents.data.model.Employee;
import rubtsov.documents.data.model.Person;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 04.11.13
 * Time: 16:32
 */
public class PersonDto implements EntityDto<Person> {

    private Long personId;

    private String firstName;

    private String lastName;

    private String patronimicName;

    private Set<EmployeeDto> assignments = new HashSet<>();

    private Set<UserDto> users = new HashSet<>();

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronimicName() {
        return patronimicName;
    }

    public void setPatronimicName(String patronimicName) {
        this.patronimicName = patronimicName;
    }

    public Set<EmployeeDto> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<EmployeeDto> assignments) {
        this.assignments = assignments;
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }

    public PersonDto() {
    }

    public PersonDto(Person person) {
        loadFromEntity(person);
    }

    @Override
    public void saveToEntity(Person person) {
        person.setFirstName(getFirstName());
        person.setLastName(getLastName());
        person.setPatronimicName(getPatronimicName());
    }

    @Override
    public void loadFromEntity(Person person) {
        personId = person.getPersonId();
        firstName = person.getFirstName();
        lastName = person.getLastName();
        patronimicName = person.getPatronimicName();

        for (Employee employee : person.getAssignments()) {
            assignments.add(new EmployeeDto(employee));
        }
    }
}
