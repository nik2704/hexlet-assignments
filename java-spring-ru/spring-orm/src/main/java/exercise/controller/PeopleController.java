package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return this.personRepository.findById(id);
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Person> createPerson(@RequestBody Map<String, String> personMap) {
        Person person = new Person();
        person.setFirstName(personMap.get("firstName"));
        person.setLastName(personMap.get("lastName"));

        this.personRepository.save(person);

        return this.personRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public Iterable<Person> deletePeopson(@PathVariable Long id) {
        Optional<Person> person = this.personRepository.findById(id);
        if (person.isPresent()) {
            this.personRepository.delete(person.get());
        }

        return this.personRepository.findAll();
    }

    @PatchMapping(path = "/{id}")
    public Iterable<Person> patchPeopson(@PathVariable Long id, @RequestBody Map<String, String> personMap) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName(personMap.get("firstName"));
        person.setLastName(personMap.get("lastName"));

        this.personRepository.save(person);

        return this.personRepository.findAll();
    }
    // END
}
