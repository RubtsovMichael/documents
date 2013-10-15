package rubtsov.documents.data.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mike on 18.07.13.
 */
@Entity
@Table(name="persons")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_PERSON")
    private Integer personId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PATRONIMIC_NAME")
    private String patronimicName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    private Set<Employee> assignments = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    private Set<User> users = new HashSet<>(0);

    public Integer getPersonId() {
        return personId;
    }

    public String getDisplayName() {
        return getLastName() + ' ' + getFirstName().substring(0, 1) + ". " + (getPatronimicName() == null ? "" : getPatronimicName().substring(0, 1) + ".");
    }

    public User getUser() {
        Set<User> users = getUsers();

        if ((users != null) && (users.size() > 0)) {
            for (User user : users) {
                return user;
            }
        }

        return null;
    }

    public void setPersonId(Integer personId) {
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

    public Set<Employee> getAssignments() {
        return assignments;
    }

    public void setAssigments(Set<Employee> assignments) {
        this.assignments = assignments;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
