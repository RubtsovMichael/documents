package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.CaseFolderDto;
import rubtsov.documents.data.model.dto.CorrespondentDto;
import rubtsov.documents.data.model.entity.CaseFolder;
import rubtsov.documents.data.model.entity.Correspondent;
import rubtsov.documents.data.repository.CasesRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if (caseFolder == null)
                throw new IllegalArgumentException("Casefolder with id [" + caseFolderDto.getCaseId() + "] is not found for update");
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
            caseFolderDtos.add(entityToDto(caseFolder));
        }

        return caseFolderDtos;
    }

    private CaseFolderDto entityToDto(CaseFolder caseFolder) {
        CaseFolderDto caseFolderDto = new CaseFolderDto(caseFolder);

        for (Correspondent correspondent : caseFolder.getCorrespondents()) {
            caseFolderDto.getCorrespondents().add(new CorrespondentDto(correspondent));
        }

        return caseFolderDto;
    }

    @Override
    public CaseFolderDto getAsDto(Long id) {
        CaseFolder caseFolder = load(id);

        if (caseFolder == null) {
            throw new IllegalArgumentException("Case folder ID [" + id + "] is not found");
        }

        return entityToDto(caseFolder);
    }

    @Override
    public Map<Long, String> getCasesAsMap() {
        HashMap<Long, String> cases = new HashMap<>();
        cases.put(Long.valueOf(-1), "");
        for (CaseFolderDto caseFolderDto : getAllCaseFoldersDtos()) {
            cases.put(caseFolderDto.getCaseId(), caseFolderDto.toString());
        }
        return cases;
    }
}
