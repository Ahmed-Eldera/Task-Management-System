package org.der3.Task_Management_System.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.der3.Task_Management_System.model.Status_enum;
@Setter
@Getter
public class TaskDTO {
    private long id;
    private String name;
    private String description;
    private Status_enum Status;
    private UserDTO user;
}
