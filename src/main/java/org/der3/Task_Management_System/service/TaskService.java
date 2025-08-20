package org.der3.Task_Management_System.service;

import org.der3.Task_Management_System.dto.TaskDTO;
import org.der3.Task_Management_System.mapper.TaskMapper;
import org.der3.Task_Management_System.model.Task;
import org.der3.Task_Management_System.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskService {
    @Autowired
    TaskRepository repo;
    @Autowired
    TaskMapper mapper;
    public TaskDTO createTask(TaskDTO task){
        Task t = mapper.toEntity(task);
        t = repo.save(t);
        return mapper.toDTO(t);
    }
    public List<TaskDTO> getTasks(long id){
        List<Task> list = repo.findByUserId(id);
        System.out.println(list);
        return mapper.toDTOList(list);
    }
    public void deleteTask(TaskDTO dto){
        Task task = mapper.toEntity(dto);
        repo.delete(task);
    }
    public TaskDTO updateTask(TaskDTO dto){
        Optional<Task> task = repo.findById(dto.getId());
        if(task.isPresent()){
            Task t = mapper.toEntity(dto);
            t = repo.save(t);

            return mapper.toDTO(t);
        }
        else {
            throw new RuntimeException("the task you are trying to update was not found") ;
        }
    }

}
