package rubtsov.documents.data.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Long caseId;

    @Column(name="CODE")
    private String code;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "caseFolder")
    private Set<Correspondent> correspondents = new HashSet<>(0);

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Correspondent> getCorrespondents() {
        return correspondents;
    }

    public void setCorrespondents(Set<Correspondent> correspondents) {
        this.correspondents = correspondents;
    }

    public CaseFolder() {
    }

    public CaseFolder(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
