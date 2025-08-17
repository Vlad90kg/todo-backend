package com.tryhard.demo.Repositories;

import com.tryhard.demo.Models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

}
