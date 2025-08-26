package org.der3.Task_Management_System.service.interfaces;

import org.der3.Task_Management_System.dto.TaskDTO;
import org.der3.Task_Management_System.mapper.TaskMapper;
import org.der3.Task_Management_System.model.Status_enum;
import org.der3.Task_Management_System.model.Task;
import org.der3.Task_Management_System.model.User;
import org.der3.Task_Management_System.repository.TaskRepository;
import org.der3.Task_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskServiceInterface {
    public List<TaskDTO> getTasksByStatus(Status_enum status);
    public TaskDTO createTask(TaskDTO taskDto) ;


    public List<TaskDTO> getTasks();


    public void deleteTask(Long taskId);


    public TaskDTO updateTask(TaskDTO taskDto) ;
}
