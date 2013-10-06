package rubtsov.documents.data.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mike on 19.07.13.
 */
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID_DEPARTMENT")
    private int departmentId;

    @Column(name="SHORT_NAME")
    private String shortName;

    @Column(name="FULL_NAME")
    private String fullName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    private Set<Employee> employees = new HashSet<>(0);

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return shortName;
    }
}
