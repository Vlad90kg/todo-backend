package com.tryhard.demo.Repositories;

import com.tryhard.demo.Models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
