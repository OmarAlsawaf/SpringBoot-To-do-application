package com.omarHussien.springbootToDoList.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "todo_item")
@NoArgsConstructor
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Boolean complete;

    @Getter
    @Setter
    private Instant creationTimeStamp;

    @Getter
    @Setter
    private Instant completionTimeStamp;

    public ToDoItem(String title) {
        this.title = title;
        this.creationTimeStamp = Instant.now();
        this.complete = false;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", complete=" + complete +
                ", creationTimeStamp=" + creationTimeStamp +
                ", completionTimeStamp=" + completionTimeStamp +
                '}';
    }
}
