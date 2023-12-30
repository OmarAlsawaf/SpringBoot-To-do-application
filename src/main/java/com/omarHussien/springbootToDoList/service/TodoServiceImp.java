package com.omarHussien.springbootToDoList.service;

import com.omarHussien.springbootToDoList.model.ToDoItem;
import com.omarHussien.springbootToDoList.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImp implements TodoService{

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @Override
    public List<ToDoItem> getTodos() {
        List<ToDoItem> todoList = new ArrayList<>();
        for (ToDoItem item : toDoItemRepository.findAll()) {
            todoList.add(item);
        }
        return todoList;
    }

    @Override
    public ToDoItem getTodoItemById(long id) {
        return toDoItemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("To-Do item not found"));
    }

    @Override
    public void updateTodo(ToDoItem toDoItem) {
        if(toDoItem.getComplete() == Boolean.FALSE){
            toDoItem.setCompletionTimeStamp(null);
        }else if (toDoItem.getComplete() == Boolean.TRUE){
            toDoItem.setCompletionTimeStamp(Instant.now());
        }
        toDoItemRepository.save(toDoItem);
    }

    @Override
    public void markTodoAsCompleted(long id) {
        ToDoItem toDoItem =  getTodoItemById(id);
        toDoItem.setCompletionTimeStamp(Instant.now());
        toDoItem.setComplete(Boolean.TRUE);
        toDoItemRepository.save(toDoItem);
    }

    @Override
    public void deleteTodo(long id) {
        ToDoItem toDoItem = getTodoItemById(id);
        toDoItemRepository.delete(toDoItem);
    }

    @Override
    public void addTodo(ToDoItem toDoItem) {
        toDoItemRepository.save(toDoItem);
    }


}
