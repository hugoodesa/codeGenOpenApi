package br.com.stapassoli.codeGenOpenApi.entity;

import com.baeldung.openapi.model.PersonRequestRepresentation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;

    public void update(PersonRequestRepresentation updateRequest) {
        this.setName(updateRequest.getName());
        this.setAge(updateRequest.getAge());
    }

}
