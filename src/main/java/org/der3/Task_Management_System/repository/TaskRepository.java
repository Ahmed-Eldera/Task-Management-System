package org.der3.Task_Management_System.repository;

import org.der3.Task_Management_System.dto.TaskDTO;
import org.der3.Task_Management_System.model.Status_enum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.der3.Task_Management_System.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(long user_id);
    List<Task> findByUserIdAndStatus(long user_id,Status_enum status);

}
