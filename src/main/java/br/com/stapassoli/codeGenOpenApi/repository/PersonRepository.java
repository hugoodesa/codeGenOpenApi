package br.com.stapassoli.codeGenOpenApi.repository;

import br.com.stapassoli.codeGenOpenApi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
