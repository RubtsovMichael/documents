package rubtsov.documents.data.model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by mrubtsov on 12.12.13.
 */
public class FileDto {

    private MultipartFile file;

    private DocumentDto document;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public DocumentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentDto document) {
        this.document = document;
    }
}
