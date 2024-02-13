package com.example.Java.Crud.controllers;

import com.example.Java.Crud.entities.ContactEntity;
import com.example.Java.Crud.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ContactController {
    @Autowired
    private ContactRepository repository;

    @GetMapping("/contacts")
    public ResponseEntity getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @PostMapping("/add-contact")
    public ResponseEntity<Object> createContact(@RequestBody ContactEntity contact) {
        ContactEntity savedContact = repository.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactEntity> updateContact(@PathVariable Integer id, @RequestBody ContactEntity updatedContact) {
        Optional<ContactEntity> existingContactOptional = repository.findById(id);
        if (existingContactOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ContactEntity existingContact = existingContactOptional.get();
        existingContact.setName(updatedContact.getName());
        existingContact.setEmail(updatedContact.getEmail());
        ContactEntity updatedEntity = repository.save(existingContact);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
