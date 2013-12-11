package rubtsov.documents.data.model.dto;

import rubtsov.documents.data.model.entity.Document;

/**
 * Created by mike on 11.12.13.
 */
public class DocumentDto {

    private Long documentId;

    private String number;

    private String description;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DocumentDto() {
    }

    public DocumentDto(Document document) {
        documentId = document.getDocumentId();
        number = document.getNumber();
        description = document.getDescription();
    }

    @Override
    public String toString() {
        return number;
    }
}
