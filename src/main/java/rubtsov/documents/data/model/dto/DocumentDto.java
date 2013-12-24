package rubtsov.documents.data.model.dto;

import rubtsov.documents.data.model.entity.Document;
import rubtsov.documents.data.model.entity.DocumentType;

import java.util.Date;

/**
 * Created by mike on 11.12.13.
 */
public class DocumentDto {

    private Long documentId;

    private DocumentType docType;

    private String outerNumber;

    private String innerNumber;

    private Date innerDate;

    private Date outerDate;

    private String description;

    private CorrespondentDto correspondent;

    private Long correspondentId;

    private PersonDto author;

    private Long authorId;

    private String outerAuthor;

    private Long caseId;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public DocumentType getDocType() {
        return docType;
    }

    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }

    public String getOuterNumber() {
        return outerNumber;
    }

    public void setOuterNumber(String outerNumber) {
        this.outerNumber = outerNumber;
    }

    public String getInnerNumber() {
        return innerNumber;
    }

    public void setInnerNumber(String innerNumber) {
        this.innerNumber = innerNumber;
    }

    public Date getInnerDate() {
        return innerDate;
    }

    public void setInnerDate(Date innerDate) {
        this.innerDate = innerDate;
    }

    public Date getOuterDate() {
        return outerDate;
    }

    public void setOuterDate(Date outerDate) {
        this.outerDate = outerDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CorrespondentDto getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(CorrespondentDto correspondent) {
        this.correspondent = correspondent;
    }

    public Long getCorrespondentId() {
        return correspondentId;
    }

    public void setCorrespondentId(Long correspondentId) {
        this.correspondentId = correspondentId;
    }

    public PersonDto getAuthor() {
        return author;
    }

    public void setAuthor(PersonDto author) {
        this.author = author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getOuterAuthor() {
        return outerAuthor;
    }

    public void setOuterAuthor(String outerAuthor) {
        this.outerAuthor = outerAuthor;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public DocumentDto() {
    }

    public DocumentDto(Document document) {
        documentId = document.getDocumentId();
        docType = document.getDocType();
        innerNumber = document.getInnerNumber();
        outerNumber = document.getOuterNumber();
        innerDate = document.getInnerDate();
        outerDate = document.getOuterDate();
        description = document.getDescription();
        outerAuthor = document.getOuterAuthor();

        if (document.getAuthor() != null) {
            author = new PersonDto(document.getAuthor());
            authorId = document.getAuthor().getPersonId();
        }
        if (document.getCorrespondent() != null) {
            correspondent = new CorrespondentDto(document.getCorrespondent());
            correspondentId = document.getCorrespondent().getCorrespondentId();
            caseId = document.getCorrespondent().getCaseFolder().getCaseId();
        }

    }

    @Override
    public String toString() {
        return innerNumber;
    }
}
