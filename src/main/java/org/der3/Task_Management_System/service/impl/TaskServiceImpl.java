package org.der3.Task_Management_System.service.impl;

import org.der3.Task_Management_System.dto.TaskDTO;
import org.der3.Task_Management_System.dto.UserDTO;
import org.der3.Task_Management_System.mapper.TaskMapper;
import org.der3.Task_Management_System.mapper.UserMapper;
import org.der3.Task_Management_System.model.Status_enum;
import org.der3.Task_Management_System.model.Task;
import org.der3.Task_Management_System.repository.TaskRepository;
import org.der3.Task_Management_System.repository.UserRepository;
import org.der3.Task_Management_System.service.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskServiceInterface {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserServiceImpl userService;


//    private User getAuthenticatedUser() {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal();
//        return userRepo.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }

    public TaskDTO createTask(TaskDTO taskDto) {
        UserDTO user = userService.getAuthenticatedUser();
        Task task = taskMapper.toEntity(taskDto);
        task.setUser(userMapper.toEntity(user)); // link task to user
        task = taskRepo.save(task);
        return taskMapper.toDTO(task);
    }


public List<TaskDTO> getTasksByStatus(Status_enum status) {
        UserDTO user = userService.getAuthenticatedUser();
        List<Task> tasks = taskRepo.findByUserIdAndStatus(user.getId(),status);
    return taskMapper.toDTOList(tasks);
}

    public List<TaskDTO> getTasks() {
        UserDTO user = userService.getAuthenticatedUser();
        List<Task> tasks = taskRepo.findByUserId( user.getId());
        return taskMapper.toDTOList(tasks);
    }


    public void deleteTask(Long taskId) {
        UserDTO user = userService.getAuthenticatedUser();
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getUser().getId() !=  user.getId()) {
            throw new RuntimeException("Unauthorized, can't delete task of another user");
        }
        taskRepo.delete(task);
    }


    public TaskDTO updateTask(TaskDTO taskDto) {
        UserDTO user = userService.getAuthenticatedUser();
        Task existingTask = taskRepo.findById(taskDto.getId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (existingTask.getUser().getId() != user.getId()) {
            throw new RuntimeException("Unauthorized, can't update task of another user");
        }


        existingTask.setName(taskDto.getName());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setDueDate(taskDto.getDueDate());
        existingTask = taskRepo.save(existingTask);

        return taskMapper.toDTO(existingTask);
    }
}
