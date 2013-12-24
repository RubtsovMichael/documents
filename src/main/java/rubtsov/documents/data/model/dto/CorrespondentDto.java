package rubtsov.documents.data.model.dto;

import rubtsov.documents.data.model.entity.Correspondent;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 29.10.13
 * Time: 13:19
 */
public class CorrespondentDto {

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
        correspondentId = correspondent.getCorrespondentId();
        displayName = correspondent.getDisplayName();
        fullName = correspondent.getFullName();
        caseFolder = new CaseFolderDto(correspondent.getCaseFolder());
    }

    @Override
    public String toString() {
        return displayName;
    }
}
