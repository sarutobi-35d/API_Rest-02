package com.API_Rest_02.controller;

import com.API_Rest_02.entity.Person;
import com.API_Rest_02.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    final PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson(){
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){

        Optional<Person> personOptional = personRepository.findById(id);

        if (personOptional.isPresent()){
            return new ResponseEntity<>(personOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Person> createdPerson(@RequestBody Person person){

        Person personSaved = personRepository.save(person);

        return new ResponseEntity<>(personSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatedPerson(@PathVariable Long id, @RequestBody Person person){

        Optional<Person> personOptional = personRepository.findById(id);

        if (personOptional.isPresent()){

            // Récupère l'entité existante en base (qui a déjà son ID)
            Person person1 = personOptional.get();

            person1.setName(person.getName());
            person1.setCity(person.getCity());

            return new ResponseEntity<>(personRepository.save(person1), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedPerson(@PathVariable Long id){

        Optional<Person> personOptional = personRepository.findById(id);

        if (personOptional.isPresent()){

            personRepository.delete(personOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
