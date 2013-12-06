package rubtsov.documents.service;

import rubtsov.documents.data.model.dto.PersonDto;
import rubtsov.documents.data.model.entity.Person;

import java.util.List;
import java.util.Map;

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

    Person saveFromDto(PersonDto personDto);

    List<PersonDto> getAllPersonsDtos();

    Map<Long, PersonDto> getPersonsAsMap();

    PersonDto getAsDto(Long id);

}
