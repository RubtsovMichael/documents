package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.DocumentDto;
import rubtsov.documents.data.model.entity.Document;
import rubtsov.documents.data.repository.DocumentsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mike on 11.12.13.
 */
@Service
public class DocumentsServiceImpl implements DocumentsService {

    @Autowired
    DocumentsRepository documentsRepository;

    @Override
    public List<Document> getAllDocs() {
        return documentsRepository.findAll();
    }

    @Override
    public Document load(Long id) {
        return documentsRepository.findOne(id);
    }

    @Override
    public Document save(Document document) {
        return documentsRepository.saveAndFlush(document);
    }

    @Override
    public Document saveFromDto(DocumentDto documentDto) {
        if (documentDto == null) {
            throw new IllegalArgumentException("Document dto required!");
        }

        if (documentDto.getDocumentId() == null) {
            throw new IllegalArgumentException("Document dto has null id!");
        }

        Document document;
        if (documentDto.getDocumentId().equals(Long.valueOf(-1L))) {
            document = new Document();
        } else {
            document = load(documentDto.getDocumentId());
            if (document == null)
                throw new IllegalArgumentException("Document with id [" + documentDto.getDocumentId() + "] is not found for update");
        }

        document.setNumber(documentDto.getNumber());
        document.setDescription(documentDto.getDescription());

        return save(document);
    }

    @Override
    public List<DocumentDto> getAllDocsDtos() {
        ArrayList<DocumentDto> documentDtos = new ArrayList<>();

        for (Document doc : getAllDocs()) {
            documentDtos.add(new DocumentDto(doc));
        }

        return documentDtos;
    }

    @Override
    public Map<Long, DocumentDto> getDocsAsMap() {
        return null;
    }

    @Override
    public DocumentDto getAsDto(Long id) {
        Document document = load(id);

        if (document == null) {
            throw new IllegalArgumentException("Document ID [" + id + "] is not found");
        }

        return new DocumentDto(document);
    }
}
