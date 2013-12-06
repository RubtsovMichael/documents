package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.EmployeeDto;
import rubtsov.documents.data.model.dto.PersonDto;
import rubtsov.documents.data.model.entity.Employee;
import rubtsov.documents.data.model.entity.Person;
import rubtsov.documents.data.repository.PersonsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Person saveFromDto(PersonDto personDto) {
        if (personDto == null) {
            throw new IllegalArgumentException("Person dto required!");
        }

        if (personDto.getPersonId() == null) {
            throw new IllegalArgumentException("Person dto has null id!");
        }

        Person person;
        if (personDto.getPersonId().equals(Long.valueOf(-1L))) {
            person = new Person();
        } else {
            person = load(personDto.getPersonId());
            if (person == null)
                throw new IllegalArgumentException("Person with id [" + personDto.getPersonId() + "] is not found for update");
        }

        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setPatronimicName(personDto.getPatronimicName());

        return save(person);
    }

    @Override
    public List<PersonDto> getAllPersonsDtos() {
        ArrayList<PersonDto> personDtos = new ArrayList<>();

        for (Person person : getAllPersons()) {
            personDtos.add(new PersonDto(person));
        }

        return personDtos;
    }

    @Override
    public Map<Long, PersonDto> getPersonsAsMap() {
        HashMap<Long, PersonDto> persons = new HashMap<>();
        for (PersonDto personDto : getAllPersonsDtos()) {
            persons.put(personDto.getPersonId(), personDto);
        }
        return persons;
    }

    @Override
    public PersonDto getAsDto(Long id) {
        Person person = load(id);

        if (person == null) {
            throw new IllegalArgumentException("Person ID is not found");
        }

        return new PersonDto(person);
    }

    private PersonDto entityToDto(Person person) {
        PersonDto personDto = new PersonDto(person);

        for (Employee employee : person.getAssignments()) {
            personDto.getAssignments().add(new EmployeeDto(employee));
        }

        return personDto;
    }
}
