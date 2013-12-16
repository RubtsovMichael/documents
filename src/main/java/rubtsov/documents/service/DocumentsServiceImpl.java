package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.DocumentDto;
import rubtsov.documents.data.model.entity.Document;
import rubtsov.documents.data.repository.CorrespondentsRepository;
import rubtsov.documents.data.repository.DocumentsRepository;
import rubtsov.documents.data.repository.PersonsRepository;

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

    @Autowired
    PersonsService personsService;

    @Autowired
    CorrespondentsService correspondentsService;

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

        document.setDocType(documentDto.getDocType());

        document.setInnerNumber(documentDto.getInnerNumber());
        document.setOuterNumber(documentDto.getOuterNumber());

        document.setInnerDate(documentDto.getInnerDate());
        document.setOuterDate(documentDto.getOuterDate());

        document.setOuterAuthor(documentDto.getOuterAuthor());
        document.setDescription(documentDto.getDescription());

        if (!documentDto.getAuthorId().equals(documentDto.getAuthor().getPersonId()))
            document.setAuthor(personsService.load(documentDto.getAuthorId()));

        if (!documentDto.getCorrespondentId().equals(documentDto.getCorrespondent().getCorrespondentId()))
            document.setCorrespondent(correspondentsService.load(documentDto.getCorrespondentId()));

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
