package com.omarHussien.springbootToDoList.controllers;


import java.time.Instant;
import java.time.ZoneId;

import com.omarHussien.springbootToDoList.model.ToDoItem;
import com.omarHussien.springbootToDoList.repository.ToDoItemRepository;
import com.omarHussien.springbootToDoList.service.TodoService;
import com.omarHussien.springbootToDoList.service.TodoServiceImp;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoItemController {
    private final Logger logger = LoggerFactory.getLogger(ToDoItemController.class);

    @Autowired
    ToDoItemRepository toDoItemRepository;
    @Autowired
    TodoService todoService;

    @GetMapping("/")
    public ModelAndView index() {
        logger.info("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems",todoService.getTodos());
        return modelAndView;
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable long id , @Valid ToDoItem toDoItem , BindingResult result, Model model){
        if(result.hasErrors()){
            toDoItem.setId(id);
            return "updateTodoItem";
        }
        todoService.updateTodo(toDoItem);
        return "redirect:/";
    }

    @PostMapping("/complete/{id}")
    public String markAsComplete(@PathVariable long id){
        todoService.markTodoAsCompleted(id);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable long id){
        todoService.deleteTodo(id);
        return "redirect:/";
    }

    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute @Valid ToDoItem toDoItem,BindingResult result){
        if(result.hasErrors()){
            return "createTodo";
        }
        todoService.addTodo(toDoItem);
        return "redirect:/";
    }






}
