package rubtsov.documents.web.dto;

import rubtsov.documents.data.model.Correspondent;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 29.10.13
 * Time: 13:19
 */
public class CorrespondentDto implements EntityDto<Correspondent>{

    private Long correspondentId;

    private String displayName;

    private String fullName;

    private CaseFolderDto caseFolder;

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

    public CaseFolderDto getCaseFolder() {
        return caseFolder;
    }

    public void setCaseFolder(CaseFolderDto caseFolder) {
        this.caseFolder = caseFolder;
    }

    public CorrespondentDto() {
    }

    public CorrespondentDto(Correspondent correspondent) {
        loadFromEntity(correspondent);
    }

    @Override
    public void saveToEntity(Correspondent correspondent) {
        correspondent.setDisplayName(getDisplayName());
        correspondent.setFullName(getFullName());
    }

    @Override
    public void loadFromEntity(Correspondent entity) {
        this.correspondentId = entity.getCorrespondentId();
        this.displayName = entity.getDisplayName();
        this.fullName = entity.getFullName();
    }
}
