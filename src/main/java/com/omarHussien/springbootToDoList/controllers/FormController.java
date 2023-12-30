package com.omarHussien.springbootToDoList.controllers;

import com.omarHussien.springbootToDoList.model.ToDoItem;
import com.omarHussien.springbootToDoList.repository.ToDoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
public class FormController {
    private Logger logger = LoggerFactory.getLogger(FormController.class);

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model){
        ToDoItem todoItem = toDoItemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("To-Do item not found"));
        model.addAttribute("todo",todoItem);
        return "updateTodoItem";
    }


}
