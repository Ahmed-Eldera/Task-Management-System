package org.der3.Task_Management_System.service;

import org.der3.Task_Management_System.dto.TaskDTO;
import org.der3.Task_Management_System.mapper.TaskMapper;
import org.der3.Task_Management_System.model.Task;
import org.der3.Task_Management_System.model.User;
import org.der3.Task_Management_System.repository.TaskRepository;
import org.der3.Task_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private TaskMapper mapper;

    @Autowired
    private UserRepository userRepo;


    private User getAuthenticatedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userRepo.findByName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public TaskDTO createTask(TaskDTO taskDto) {
        User user = getAuthenticatedUser();
        Task task = mapper.toEntity(taskDto);
        task.setUserId((int) user.getId()); // link task to user
        task = taskRepo.save(task);
        return mapper.toDTO(task);
    }


    public List<TaskDTO> getTasks() {
        User user = getAuthenticatedUser();
        List<Task> tasks = taskRepo.findByUserId((int) user.getId());
        return mapper.toDTOList(tasks);
    }


    public void deleteTask(Long taskId) {
        User user = getAuthenticatedUser();
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getUserId() != (int) user.getId()) {
            throw new RuntimeException("Unauthorized, can't delete task of another user");
        }
        taskRepo.delete(task);
    }


    public TaskDTO updateTask(TaskDTO taskDto) {
        User user = getAuthenticatedUser();
        Task existingTask = taskRepo.findById(taskDto.getId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (existingTask.getUserId() != (int) user.getId()) {
            throw new RuntimeException("Unauthorized, can't update task of another user");
        }


        existingTask.setName(taskDto.getName());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStatus(taskDto.getStatus());
        existingTask = taskRepo.save(existingTask);

        return mapper.toDTO(existingTask);
    }
}
