package org.der3.Task_Management_System.controller;

import org.der3.Task_Management_System.dto.TaskDTO;
import org.der3.Task_Management_System.service.impl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

@PostMapping
public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
    TaskDTO created = taskService.createTask(taskDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}



    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<TaskDTO> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable long id, @RequestBody TaskDTO taskDTO) {
        taskDTO.setId(id);
        TaskDTO updated = taskService.updateTask(taskDTO);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
