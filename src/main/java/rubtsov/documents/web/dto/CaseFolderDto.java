package rubtsov.documents.web.dto;

import rubtsov.documents.data.model.CaseFolder;
import rubtsov.documents.data.model.Correspondent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mike on 25.10.13.
 */
public class CaseFolderDto implements EntityDto<CaseFolder>{

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
        loadFromEntity(caseFolder);
    }

    @Override
    public void saveToEntity(CaseFolder entity) {
        entity.setCode(getCode());
        entity.setName(getName());
        entity.setDescription(getDescription());
    }

    @Override
    public void loadFromEntity(CaseFolder entity) {
        caseId = entity.getCaseId();
        name = entity.getName();
        description = entity.getDescription();
        code = entity.getCode();

        for (Correspondent correspondent : entity.getCorrespondents()) {
            CorrespondentDto correspondentDto = new CorrespondentDto(correspondent);
            correspondentDto.setCaseFolder(this);
            correspondents.add(correspondentDto);
        }
    }

}
