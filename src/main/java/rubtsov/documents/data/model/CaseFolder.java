package rubtsov.documents.data.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 14.10.13
 * Time: 17:15
 */
@Entity
@Table(name = "case_folders")
public class CaseFolder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID_CASE")
    private int caseId;

    @Column(name="CODE")
    private String code;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
