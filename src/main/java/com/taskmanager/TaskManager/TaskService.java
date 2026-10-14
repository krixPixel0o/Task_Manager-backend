
package com.taskmanager.TaskManager;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, boolean completed) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id));

        task.setCompleted(completed);

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        taskRepository.deleteById(id);
    }
}
