package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.Correspondent;
import rubtsov.documents.data.repository.CorrespondentsRepository;

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
}
