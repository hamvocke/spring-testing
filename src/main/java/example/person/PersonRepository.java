package example.person;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {

    Optional<Person> findByLastName(String lastName);

}
