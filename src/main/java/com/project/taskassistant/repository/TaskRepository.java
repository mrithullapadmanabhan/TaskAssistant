package com.project.taskassistant.repository;

import com.project.taskassistant.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    public List<Task> findByUser_UserId(String userId);
}
