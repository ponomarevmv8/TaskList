package ru.petproject.taskList.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.petproject.taskList.dto.task.TaskDto;
import ru.petproject.taskList.dto.validation.OnUpdate;
import ru.petproject.taskList.entity.task.Task;
import ru.petproject.taskList.mapper.TaskMapper;
import ru.petproject.taskList.service.TaskService;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task controller", description = "Task API")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    public TaskDto getById(@PathVariable Long id){
        Task task = taskService.getById(id);
        return taskMapper.taskToDto(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id")
    public void deleteById(@PathVariable Long id){
        taskService.delete(id);
    }

    @PutMapping
    @Operation(summary = "Update task")
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.taskDtoToEntity(taskDto);
        Task updateTask = taskService.update(task);
        return  taskMapper.taskToDto(updateTask);
    }


}
