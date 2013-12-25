package rubtsov.documents.web.Commands;

/**
 * Created by mrubtsov on 25.12.13.
 */
public class CaseIdCommand {

    Long caseId;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public CaseIdCommand() {
    }

    public CaseIdCommand(Long caseId) {
        this.caseId = caseId;
    }
}
