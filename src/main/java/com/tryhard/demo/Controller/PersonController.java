package com.tryhard.demo.Controller;

import com.tryhard.demo.Models.Person;
import com.tryhard.demo.Models.Todo;
import com.tryhard.demo.Repositories.PersonRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RequestMapping("api/person")
@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public  Iterable<Person> getAllPeople() {
        return this.personRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Person getOne(@PathVariable Integer id) {
        return this.personRepository.findById(id).orElse(null);
    }

    @GetMapping(path = "/{id}/todos")
    public Iterable<Todo> getTodosForPerson (@PathVariable Integer id) {
        System.out.println("getTodosForPerson with id: " + id);

        Optional<Person> person = this.personRepository.findById(id);
        if (person.isPresent()) {

            return person.get().getTodos();

        }else {
            System.out.println("Person not found with id: " + id);
            return null;
        }
    }

    @GetMapping(path = "/{id}/todos/date")
    public Iterable<Todo> getTodosForDate (@PathVariable Integer id, @RequestParam(name = "due_date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date due) {
        System.out.println("Getting todos before date " + due.toString() + " with person id: " + id);
        Optional<Person> person = this.personRepository.findById(id);
        if (person.isPresent()) {
            Person p = person.get();
            return  p.getTodosBeforeDate(due);
        } else  {
            System.out.println("Person not found with id: " + id);
            return null;
        }
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        System.out.println("Creating person with id: " + person.getId());
        return this.personRepository.save(person);
    }

    @PutMapping(path = "/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable Integer id) {
        System.out.println("Updating person with id: " + id);
        Optional<Person> personToUpdate = this.personRepository.findById(id);
        if(personToUpdate.isPresent()) {
            Person p = personToUpdate.get();
            if(person.getName() != null) {
                p.setName(person.getName());
            }
            if(person.getEmail() != null) {
                p.setEmail(person.getEmail());
            }
            if(person.getPassword() != null) {
                p.setPassword(person.getPassword());
            }
            return this.personRepository.save(p);
        } else {
            System.out.println("Person not found with id: " + id);
            return null;
        }
    }

    @DeleteMapping(path = "/{id}")
    public Person deletePerson(@PathVariable Integer id) {
        System.out.println("Deleting person with id: " + id);
        Optional<Person> personToDelete = this.personRepository.findById(id);
        if(personToDelete.isPresent()) {
            this.personRepository.delete(personToDelete.get());
            return personToDelete.get();
        } else {
            System.out.println("Person not found with id: " + id);
            return null;
        }
    }


}
