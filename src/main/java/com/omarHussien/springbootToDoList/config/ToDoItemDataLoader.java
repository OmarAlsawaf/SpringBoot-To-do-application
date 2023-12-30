package com.omarHussien.springbootToDoList.config;

import com.omarHussien.springbootToDoList.model.ToDoItem;
import com.omarHussien.springbootToDoList.repository.ToDoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


//this class is used to generate seed data just for testing purposes
@Component
public class ToDoItemDataLoader implements CommandLineRunner {

    @Autowired
    ToDoItemRepository toDoItemRepository;

    private final Logger logger = LoggerFactory.getLogger(ToDoItemDataLoader.class);
    @Override
    public void run(String... args) throws Exception {
        generateSeedData();
    }

    private void generateSeedData(){
        //only generate data if database is empty
        if(toDoItemRepository.count() ==0){
            logger.info("generating seed data ....");
            ToDoItem item1 = new ToDoItem("dummy task 1");
            ToDoItem item2 = new ToDoItem("dummy task 2");
            toDoItemRepository.save(item1);
            toDoItemRepository.save(item2);
        }
        logger.info("Number of tasks : " + toDoItemRepository.count());
    }
}
