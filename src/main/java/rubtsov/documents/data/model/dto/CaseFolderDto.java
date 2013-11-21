package rubtsov.documents.data.model.dto;

import rubtsov.documents.data.model.entity.CaseFolder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mike on 25.10.13.
 */
public class CaseFolderDto {

    private Long caseId;

    private String code;

    private String name;

    private String description;

    private Set<CorrespondentDto> correspondents =  new HashSet<>();

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

    public Set<CorrespondentDto> getCorrespondents() {
        return correspondents;
    }

    public void setCorrespondents(Set<CorrespondentDto> correspondents) {
        this.correspondents = correspondents;
    }

    public CaseFolderDto() {
    }

    public CaseFolderDto(CaseFolder caseFolder) {
        caseId = caseFolder.getCaseId();
        name = caseFolder.getName();
        description = caseFolder.getDescription();
        code = caseFolder.getCode();
    }
}
