package org.der3.Task_Management_System.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "tasks")
public class Task {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;


    private Status_enum status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
