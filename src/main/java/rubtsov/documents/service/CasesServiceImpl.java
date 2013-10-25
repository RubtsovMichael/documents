package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.CaseFolder;
import rubtsov.documents.data.repository.CasesRepository;

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
}
