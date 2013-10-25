package rubtsov.documents.web.dto;

import rubtsov.documents.data.model.CaseFolder;
import sun.print.resources.serviceui_it;

import java.io.Serializable;

/**
 * Created by mike on 25.10.13.
 */
public class CaseFolderDto implements Serializable {

    private Long caseId;

    private String code;

    private String name;

    private String description;

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

    public CaseFolderDto() {
    }

    public CaseFolderDto(CaseFolder caseFolder) {
        caseId = caseFolder.getCaseId();
        name = caseFolder.getName();
        description = caseFolder.getDescription();
        code = caseFolder.getCode();
    }

    public void saveToEntity(CaseFolder caseFolder) {
        caseFolder.setCode(getCode());
        caseFolder.setName(getName());
        caseFolder.setDescription(getDescription());
    }
}
