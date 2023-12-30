package com.omarHussien.springbootToDoList.service;

import com.omarHussien.springbootToDoList.model.ToDoItem;

import java.util.List;

public interface TodoService {

    public List<ToDoItem> getTodos();

    public ToDoItem getTodoItemById(long id);

    public void updateTodo(ToDoItem toDoItem);

    public void markTodoAsCompleted(long id);

    public void deleteTodo(long id);

    public void addTodo(ToDoItem toDoItem);


}
