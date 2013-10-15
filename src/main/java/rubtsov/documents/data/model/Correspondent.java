package rubtsov.documents.data.model;

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
    private int correspondentId;

    @Column(name="display_name")
    private String displayName;

    @Column(name="full_name")
    private String fullName;

    @Column(name="prefix")
    private String prefix;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_case")
    private CaseFolder caseFolder;

    public int getCorrespondentId() {
        return correspondentId;
    }

    public void setCorrespondentId(int correspondentId) {
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public CaseFolder getCaseFolder() {
        return caseFolder;
    }

    public void setCaseFolder(CaseFolder caseFolder) {
        this.caseFolder = caseFolder;
    }

    public Correspondent() {
    }

    public Correspondent(String displayName, String fullName, String prefix, CaseFolder caseFolder) {
        this.displayName = displayName;
        this.fullName = fullName;
        this.prefix = prefix;
        this.caseFolder = caseFolder;
    }
}
