package rubtsov.documents.data.model.entity;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name="doc_type")
    private DocumentType docType;

    @Column(name="OUTER_NUMBER")
    private String outerNumber;

    @Column(name="INNER_NUMBER")
    private String innerNumber;

    @Column(name="INNER_DATE")
    private Date innerDate;

    @Column(name="OUTER_DATE")
    private Date outerDate;

    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_correspondent")
    private Correspondent correspondent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_author")
    private Person author;

    @Column(name="outer_author")
    private String outerAuthor;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Correspondent getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(Correspondent correspondent) {
        this.correspondent = correspondent;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getOuterAuthor() {
        return outerAuthor;
    }

    public void setOuterAuthor(String outerAuthor) {
        this.outerAuthor = outerAuthor;
    }
}
