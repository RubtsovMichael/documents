package rubtsov.documents.service;

import rubtsov.documents.data.model.dto.CorrespondentDto;
import rubtsov.documents.data.model.entity.Correspondent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 29.10.13
 * Time: 15:33
 */
public interface CorrespondentsService {

    List<Correspondent> getAllCorrespondents();

    Correspondent load(Long id);

    Correspondent save(Correspondent correspondent);

    Correspondent saveFromDto(CorrespondentDto correspondentDto);

    List<CorrespondentDto> getAllCorrespondentsDtos();

    CorrespondentDto getAsDto(Long id);
}
