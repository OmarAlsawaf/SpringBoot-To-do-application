package com.omarHussien.springbootToDoList.repository;

import com.omarHussien.springbootToDoList.model.ToDoItem;
import org.springframework.data.repository.CrudRepository;

public interface ToDoItemRepository extends CrudRepository<ToDoItem,Long> {
    // this will auto generate a spring bean of ToDoItemRepository that implements all methods in CrudRepository interface
}
