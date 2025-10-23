package com.project.taskassistant.service;

import com.project.taskassistant.model.Task;
import com.project.taskassistant.model.TaskRequestDTO;
import com.project.taskassistant.model.User;
import com.project.taskassistant.repository.TaskRepository;
import com.project.taskassistant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getByTaskId(String id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByUserId(String id) {
        return taskRepository.findByUser_UserId(id);
    }

    public Task createTask(TaskRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCategory(dto.getCategory());
        task.setStatus(dto.getStatus());
        task.setUser(user);

        return taskRepository.save(task);
    }


    public Task updateTask(String id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setCategory(updatedTask.getCategory());
                    existingTask.setStatus(updatedTask.getStatus());
                    return taskRepository.save(existingTask);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }


    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
