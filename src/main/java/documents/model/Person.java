package documents.model;

import javax.persistence.*;

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

    public Integer getPersonId() {
        return personId;
    }

    public String getDisplayName() {
        return getLastName() + ' ' + getFirstName().substring(0, 1) + ". " + (getPatronimicName() == null ? "" : getPatronimicName().substring(0, 1) + ".");
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
}
