package com.tryhard.demo.Controller;


import com.tryhard.demo.Models.Todo;
import com.tryhard.demo.Repositories.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/todo")
@RestController
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public Iterable<Todo> getAllTodos(){
        return this.todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable Integer id){
        System.out.println("Get todo by id: " + id);
        return this.todoRepository.findById(id);
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        System.out.println("Addong new todo " + todo.toString());
        return this.todoRepository.save(todo);
    }

    @PutMapping ("/{id}")Todo todoUpdate(@RequestBody Todo todo, @PathVariable Integer id){
        System.out.println("Update todo by id: " + id);
        Optional<Todo> optionalTodo = this.todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo updatedTodo = optionalTodo.get();
            if(todo.getName() != null) {
                updatedTodo.setName(todo.getName());
            }
            if(todo.getDue_date() != null) {
                updatedTodo.setDue_date(todo.getDue_date());
            }
            if(todo.getDate_added() != null) {
                updatedTodo.setDate_added(todo.getDate_added());
            }
            if(todo.getPerson_id() != null) {
                updatedTodo.setPerson_id(todo.getPerson_id());
            }

            return  this.todoRepository.save(updatedTodo);
        } else {
            System.out.println("Could not locate Todo");
            return null;
        }

    }
    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable Integer id){
            this.todoRepository.deleteById(id);
        
    }

}
