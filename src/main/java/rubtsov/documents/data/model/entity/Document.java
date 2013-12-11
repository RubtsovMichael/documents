package rubtsov.documents.data.model.entity;

import javax.persistence.*;

/**
 * Created by mike on 11.12.13.
 */
@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID_DOC")
    private Long documentId;

    @Column(name="NUMBER")
    private String number;

    @Column(name="DESCRIPTION")
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
}
