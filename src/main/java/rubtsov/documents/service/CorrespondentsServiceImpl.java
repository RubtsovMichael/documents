package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.CorrespondentDto;
import rubtsov.documents.data.model.entity.Correspondent;
import rubtsov.documents.data.repository.CorrespondentsRepository;

import java.util.ArrayList;
import java.util.List;

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
    public List<Correspondent> getAllCorrespondents() {
        return correspondentsRepository.findAll();
    }

    @Override
    public Correspondent load(Long id) {
        return correspondentsRepository.findOne(id);
    }

    @Override
    public Correspondent save(Correspondent correspondent) {
        return correspondentsRepository.saveAndFlush(correspondent);
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
}
