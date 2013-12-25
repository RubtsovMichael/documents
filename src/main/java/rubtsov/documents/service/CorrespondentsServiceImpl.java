package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.CorrespondentDto;
import rubtsov.documents.data.model.entity.Correspondent;
import rubtsov.documents.data.repository.CorrespondentsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 29.10.13
 * Time: 15:35
 */
@Service
public class CorrespondentsServiceImpl implements CorrespondentsService {

    @Autowired
    private CorrespondentsRepository correspondentsRepository;

    @Autowired
    private CasesService casesService;

    @Override
    public List<Correspondent> getByCaseFolderId(Long caseId) {
        return correspondentsRepository.getByCaseFolderId(caseId);
    }

    @Override
    public Map<Long, String> getAsMapByCaseFolderId(Long caseId) {
        HashMap<Long, String> corrs = new HashMap<>();
        for (Correspondent correspondent : getByCaseFolderId(caseId)) {
            corrs.put(correspondent.getCorrespondentId(), (new CorrespondentDto(correspondent)).toString());
        }
        return corrs;
    }

    @Override
    public List<Correspondent> getAllCorrespondents() {
        return null;//correspondentsRepository.findAll();
    }

    @Override
    public Correspondent load(Long id) {
        return correspondentsRepository.findOne(id);
    }

    @Override
    public Correspondent save(Correspondent correspondent) {
        return correspondentsRepository.save(correspondent);
    }

    @Override
    public Correspondent saveFromDto(CorrespondentDto correspondentDto) {
        if (correspondentDto == null) {
            throw new IllegalArgumentException("Correspondent dto required!");
        }

        if (correspondentDto.getCorrespondentId() == null) {
            throw new IllegalArgumentException("Correspondent dto has null id!");
        }

        Correspondent correspondent;
        if (correspondentDto.getCorrespondentId().equals(Long.valueOf(-1L))) {
            correspondent = new Correspondent();
        } else {
            correspondent = load(correspondentDto.getCorrespondentId());
            if (correspondent == null)
                throw new IllegalArgumentException("Correspondent with id [" + correspondentDto.getCorrespondentId() + "] is not found for update");
        }

        correspondent.setFullName(correspondentDto.getFullName());
        correspondent.setDisplayName(correspondentDto.getDisplayName());
        correspondent.setCaseFolder(casesService.load(correspondentDto.getCaseFolder().getCaseId()));

        return save(correspondent);
    }

    @Override
    public List<CorrespondentDto> getAllCorrespondentsDtos() {
        ArrayList<CorrespondentDto> correspondentDtos = new ArrayList<>();

        for (Correspondent correspondent : getAllCorrespondents()) {
            correspondentDtos.add(new CorrespondentDto(correspondent));
        }

        return correspondentDtos;
    }

    @Override
    public CorrespondentDto getAsDto(Long id) {
        Correspondent correspondent = load(id);

        if (correspondent == null) {
            throw new IllegalArgumentException("Correspondent with ID [" + id + "] is not found");
        }

        return new CorrespondentDto(correspondent);
    }
}
