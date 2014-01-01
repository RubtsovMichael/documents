package rubtsov.documents.web.Commands;

import rubtsov.documents.data.model.entity.DocumentType;

/**
 * Created by mrubtsov on 25.12.13.
 */
public class DocControlsCommand {

    Long caseId;

    DocumentType docType;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public DocumentType getDocType() {
        return docType;
    }

    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }

    public DocControlsCommand() {
    }

    public DocControlsCommand(Long caseId, DocumentType docType) {
        this.caseId = caseId;
        this.docType = docType;
    }
}
