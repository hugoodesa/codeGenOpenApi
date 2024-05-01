package br.com.stapassoli.codeGenOpenApi.controller;

import br.com.stapassoli.codeGenOpenApi.service.PersonService;
import com.baeldung.openapi.api.ApiApi;
import com.baeldung.openapi.model.PersonRequestRepresentation;
import com.baeldung.openapi.model.PersonResponseRepresentation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonController implements ApiApi {

    private final PersonService personService;

    @Override//GET /api/persons/{id}
    public ResponseEntity<PersonResponseRepresentation> apiPersonsIdGet(Integer id) {
        PersonResponseRepresentation response = personService.getPersonById(id);
        return ResponseEntity.ok(response);
    }

    @Override//GET /api/persons
    public ResponseEntity<List<PersonResponseRepresentation>> apiPersonsGet() {
        List<PersonResponseRepresentation> response = personService.getAllPerson();
        return ApiApi.super.apiPersonsGet();
    }

    @Override//DELETE /api/persons/{id}
    public ResponseEntity<Void> apiPersonsIdDelete(Integer id) {
        personService.deleteById(id);
        return ApiApi.super.apiPersonsIdDelete(id);
    }

    @Override//PUT /api/persons/{id}
    public ResponseEntity<PersonResponseRepresentation> apiPersonsIdPut(Integer id, PersonRequestRepresentation personRequestRepresentation) {
        PersonResponseRepresentation person = personService.updatePerson(id);
        return ApiApi.super.apiPersonsIdPut(id, personRequestRepresentation);
    }

    @Override//POST /api/persons
    public ResponseEntity<PersonResponseRepresentation> apiPersonsPost(PersonRequestRepresentation personRequestRepresentation) {
        PersonResponseRepresentation person = personService.createPerson(personRequestRepresentation);
        return ApiApi.super.apiPersonsPost(personRequestRepresentation);
    }
}
