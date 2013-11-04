package rubtsov.documents.service;

import rubtsov.documents.data.model.Person;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 04.11.13
 * Time: 16:24
 */
public interface PersonsService {

    List<Person> getAllPersons();

    Person load(Long id);

    Person save(Person person);

}
