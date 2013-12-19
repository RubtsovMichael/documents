package rubtsov.documents.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rubtsov.documents.data.model.dto.PersonDto;
import rubtsov.documents.service.PersonsService;
import rubtsov.documents.web.utils.Views;

/**
 * Created by mrubtsov on 10.12.13.
 */
@Controller
@RequestMapping(Views.PERSONS + "/**")
public class PersonsController {

    Logger LOG = org.slf4j.LoggerFactory.getLogger(PersonsController.class);

    @Autowired
    PersonsService personsService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPersons(ModelMap model) {

        model.put("persons", personsService.getAllPersonsDtos());

        return Views.PERSONS;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.PERSONS + "/{personId}")
    public String getPersonForm(@PathVariable Long personId, ModelMap model) {

        PersonDto personDto;
        if (personId == -1) {
            personDto = new PersonDto();
            personDto.setPersonId(Long.valueOf(-1));
        } else {
            personDto = personsService.getAsDto(personId);
        }

        model.put("personCommand", personDto);

        return Views.PERSON_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("personCommand") PersonDto personDto) {

        if (personDto == null) {
            throw new IllegalArgumentException("A personDto is required");
        }

        LOG.debug("Submitted personId " + personDto.getPersonId());

        personsService.saveFromDto(personDto);

        return "redirect:" + Views.PERSONS;
    }

}
