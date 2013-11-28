package rubtsov.documents.service;

import rubtsov.documents.data.model.dto.CaseFolderDto;
import rubtsov.documents.data.model.entity.CaseFolder;

import java.util.List;

/**
 * Created by mike on 25.10.13.
 */
public interface CasesService {

    List<CaseFolder> getAllCaseFolders();

    CaseFolder load(Long id);

    CaseFolder save(CaseFolder caseFolder);

    CaseFolder saveFromDto(CaseFolderDto caseFolderDto);

    List<CaseFolderDto> getAllCaseFoldersDtos();

    CaseFolderDto getAsDto(Long id);

}
