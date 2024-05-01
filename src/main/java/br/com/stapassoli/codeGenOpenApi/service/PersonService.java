package br.com.stapassoli.codeGenOpenApi.service;

import br.com.stapassoli.codeGenOpenApi.entity.Person;
import br.com.stapassoli.codeGenOpenApi.repository.PersonRepository;
import com.baeldung.openapi.model.PersonRequestRepresentation;
import com.baeldung.openapi.model.PersonResponseRepresentation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void initClass() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public PersonResponseRepresentation getPersonById(Integer id) {
        Person person = this.personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not possible to find the person"));
        return objectMapper.convertValue(person,PersonResponseRepresentation.class);
    }


    public List<PersonResponseRepresentation> getAllPerson() {
        return null;
    }

    public void deleteById(Integer id) {
    }

    public PersonResponseRepresentation updatePerson(Integer id) {
        return null;
    }

    public PersonResponseRepresentation createPerson(PersonRequestRepresentation personRequestRepresentation) {
        return null;
    }
}
