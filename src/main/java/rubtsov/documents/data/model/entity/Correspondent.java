package rubtsov.documents.data.model.entity;

import javax.persistence.*;

/**
 * Created by mike on 19.07.13.
 */
@Entity
@Table(name = "correspondents")
public class Correspondent {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_correspondent")
    private Long correspondentId;

    @Column(name="display_name")
    private String displayName;

    @Column(name="full_name")
    private String fullName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_case")
    private CaseFolder caseFolder;

    public Long getCorrespondentId() {
        return correspondentId;
    }

    public void setCorrespondentId(Long correspondentId) {
        this.correspondentId = correspondentId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CaseFolder getCaseFolder() {
        return caseFolder;
    }

    public void setCaseFolder(CaseFolder caseFolder) {
        this.caseFolder = caseFolder;
    }

    public Correspondent() {
    }

    public Correspondent(String displayName, String fullName, CaseFolder caseFolder) {
        this.displayName = displayName;
        this.fullName = fullName;
        this.caseFolder = caseFolder;
    }
}
