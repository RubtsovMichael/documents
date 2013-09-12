package documents.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mike on 19.07.13.
 */
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_employee")
    private int employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person")
    private Person person;

    @Temporal(TemporalType.DATE)
    @Column(name="date_beg")
    private Date dateBegin;

    @Temporal(TemporalType.DATE)
    @Column(name="date_end")
    private Date dateEnd;

    public String getDisplayName() {
        return getPerson().getDisplayName() + ", " + getPost().getDisplayName() + " " + getDepartment().getShortName();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
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
}