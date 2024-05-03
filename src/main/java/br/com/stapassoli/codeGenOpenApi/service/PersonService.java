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
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public List<PersonResponseRepresentation> getAllPerson(Pageable pageable) {
        Page<Person> all = personRepository.findAll(pageable);
        return all.stream().map(person -> objectMapper.convertValue(person, PersonResponseRepresentation.class)).toList();
    }

    public void deleteById(Integer id) {
        try {
            this.personRepository.deleteById(id);
        }catch (Exception e) {
            notFoundEntity();
        }
    }

    public void notFoundEntity () throws EntityNotFoundException {
        throw new EntityNotFoundException("Not found Entity");
    }

    public PersonResponseRepresentation updatePerson(Integer id, PersonRequestRepresentation personRequestRepresentation) {
        Optional<Person> optionalPerson = this.personRepository.findById(id);

        Person person = optionalPerson.orElseThrow(() -> new EntityNotFoundException("Not found Entity"));
        person.update(personRequestRepresentation);

        person = this.personRepository.save(person);

        return objectMapper.convertValue(person, PersonResponseRepresentation.class);
    }

    public PersonResponseRepresentation createPerson(PersonRequestRepresentation request) {
        Person person = objectMapper.convertValue(request, Person.class);
        person= this.personRepository.save(person);

        return objectMapper.convertValue(person, PersonResponseRepresentation.class);
    }
}
