package rubtsov.documents.service;

import rubtsov.documents.data.model.dto.DocumentDto;
import rubtsov.documents.data.model.entity.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by mike on 11.12.13.
 */
public interface DocumentsService {
    List<Document> getAllDocs();

    Document load(Long id);

    Document save(Document document);

    Document saveFromDto(DocumentDto documentDto);

    List<DocumentDto> getAllDocsDtos();

    Map<Long, DocumentDto> getDocsAsMap();

    DocumentDto getAsDto(Long id);
}
