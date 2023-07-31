package ru.petproject.taskList.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.petproject.taskList.entity.task.Status;
import ru.petproject.taskList.entity.task.Task;
import ru.petproject.taskList.exception.ResourceNotFoundException;
import ru.petproject.taskList.repository.TaskRepository;
import ru.petproject.taskList.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {

        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }
        taskRepository.update(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {
        task.setStatus(Status.TODO);
        taskRepository.create(task);
        taskRepository.assignToUserById(task.getId(), userId);
        return task;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.delete(id);
    }
}
