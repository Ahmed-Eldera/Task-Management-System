package org.der3.Task_Management_System.controller;

import org.der3.Task_Management_System.dto.TaskDTO;
import org.der3.Task_Management_System.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // CREATE
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO created = taskService.createTask(taskDTO);
        return ResponseEntity.ok(created);
    }

    // READ (all tasks for a given user id)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable long userId) {
        List<TaskDTO> tasks = taskService.getTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable long id, @RequestBody TaskDTO taskDTO) {
        taskDTO.setId(id); // ensure path id is used
        TaskDTO updated = taskService.updateTask(taskDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        TaskDTO dto = new TaskDTO();
        dto.setId(id);
        taskService.deleteTask(dto);
        return ResponseEntity.noContent().build();
    }
}
