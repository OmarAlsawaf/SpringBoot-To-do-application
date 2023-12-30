package com.omarHussien.springbootToDoList.controllers;


import java.time.Instant;
import java.time.ZoneId;

import com.omarHussien.springbootToDoList.model.ToDoItem;
import com.omarHussien.springbootToDoList.repository.ToDoItemRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoItemController {
    private final Logger logger = LoggerFactory.getLogger(ToDoItemController.class);

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @GetMapping("/")
    public ModelAndView index() {
        logger.info("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems",toDoItemRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable long id , @Valid ToDoItem toDoItem , BindingResult result, Model model){
        if(result.hasErrors()){
            toDoItem.setId(id);
            return "updateTodoItem";
        }
        if(toDoItem.getComplete() == Boolean.FALSE){
            toDoItem.setCompletionTimeStamp(null);
        }
        toDoItemRepository.save(toDoItem);
        return "redirect:/";
    }

    @PostMapping("/complete/{id}")
    public String markAsComplete(@PathVariable long id){
        ToDoItem todoItem = toDoItemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("To-Do item not found"));
        todoItem.setCompletionTimeStamp(Instant.now());
        todoItem.setComplete(Boolean.TRUE);
        toDoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable long id){
        ToDoItem todoItem = toDoItemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("To-Do item not found"));
        toDoItemRepository.delete(todoItem);
        return "redirect:/";
    }






}
