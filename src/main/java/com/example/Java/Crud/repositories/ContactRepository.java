package com.example.Java.Crud.repositories;

import com.example.Java.Crud.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {
}
