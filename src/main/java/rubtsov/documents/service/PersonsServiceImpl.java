package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.Person;
import rubtsov.documents.data.repository.PersonsRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 04.11.13
 * Time: 16:25
 */
@Service
public class PersonsServiceImpl implements PersonsService {

    @Autowired
    PersonsRepository personsRepository;

    @Override
    public List<Person> getAllPersons() {
        return personsRepository.findAll();
    }

    @Override
    public Person load(Long id) {
        return personsRepository.findOne(id);
    }

    @Override
    public Person save(Person person) {
        return personsRepository.saveAndFlush(person);
    }
}
