package org.der3.Task_Management_System.mapper;

import org.der3.Task_Management_System.dto.TaskDTO;
import org.der3.Task_Management_System.dto.UserDTO;
import org.der3.Task_Management_System.model.Task;
import org.der3.Task_Management_System.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {
    @Autowired
    UserMapper userMapper;
    public TaskDTO toDTO(Task t){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(t.getId());
        taskDTO.setName(t.getName());
        taskDTO.setDescription(t.getDescription());
        taskDTO.setStatus(t.getStatus());
        taskDTO.setUser(userMapper.toDTO(t.getUser()));
        taskDTO.setDueDate(t.getDueDate());
        return taskDTO;
    }
        public Task toEntity(TaskDTO t){
        Task task = new Task();
        task.setUser(new User());
        task.setId(t.getId());
        task.setName(t.getName());
        task.setDescription(t.getDescription());
        task.setStatus(t.getStatus());
        t.setUser(new UserDTO());
        task.getUser().setId(t.getUser().getId());
        task.setDueDate(t.getDueDate());
        return task;
    }
    public List<Task> toTaskList(List<TaskDTO> dtoList){
        List<Task> taskList = new ArrayList<>();
        for( int i =0 ; i< dtoList.size();i++){
            Task t = toEntity(dtoList.get(i));
            taskList.add(t);
        }
        return taskList;
    }
        public List<TaskDTO> toDTOList(List<Task> taskList){
        List<TaskDTO> dtoList = new ArrayList<>();
        for( int i =0 ; i< taskList.size();i++){
            TaskDTO t = toDTO(taskList.get(i));
            dtoList.add(t);
        }
        return dtoList;
    }
}
