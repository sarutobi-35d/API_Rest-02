package com.API_Rest_02.repository;

import com.API_Rest_02.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
