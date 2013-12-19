package rubtsov.documents.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by mrubtsov on 19.12.13.
 */
public interface FileUploadService {

    boolean saveDocumentImage(Long docId, MultipartFile file);

}
