package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.CaseFolderDto;
import rubtsov.documents.data.model.entity.CaseFolder;
import rubtsov.documents.data.repository.CasesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 25.10.13.
 */
@Service
public class CasesServiceImpl implements CasesService {

    @Autowired
    private CasesRepository casesRepository;

    @Override
    public List<CaseFolder> getAllCaseFolders() {
        return casesRepository.findAll();
    }

    @Override
    public CaseFolder load(Long id) {
        return casesRepository.findOne(id);
    }

    @Override
    public CaseFolder save(CaseFolder caseFolder) {
        return casesRepository.saveAndFlush(caseFolder);
    }

    @Override
    public CaseFolder saveFromDto(CaseFolderDto caseFolderDto) {
        if (caseFolderDto == null) {
            throw new IllegalArgumentException("Case dto required!");
        }

        if (caseFolderDto.getCaseId() == null) {
            throw new IllegalArgumentException("Case dto has null id!");
        }

        CaseFolder caseFolder;
        if (caseFolderDto.getCaseId().equals(Long.valueOf(-1L))) {
            caseFolder = new CaseFolder();
        } else {
            caseFolder = load(caseFolderDto.getCaseId());
        }

        caseFolder.setCode(caseFolderDto.getCode());
        caseFolder.setName(caseFolderDto.getName());
        caseFolder.setDescription(caseFolderDto.getDescription());

        return save(caseFolder);
    }

    @Override
    public List<CaseFolderDto> getAllCaseFoldersDtos() {
        ArrayList<CaseFolderDto> caseFolderDtos = new ArrayList<>();

        for (CaseFolder caseFolder : getAllCaseFolders()) {
            caseFolderDtos.add(new CaseFolderDto(caseFolder));
        }

        return caseFolderDtos;
    }
}
