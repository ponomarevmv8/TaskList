package ru.petproject.taskList.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.petproject.taskList.dto.task.TaskDto;
import ru.petproject.taskList.dto.task.TaskImageDto;
import ru.petproject.taskList.dto.validation.OnUpdate;
import ru.petproject.taskList.entity.task.Task;
import ru.petproject.taskList.entity.task.TaskImage;
import ru.petproject.taskList.mapper.TaskImageMapper;
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
    private final TaskImageMapper taskImageMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    @PreAuthorize("canAccessTask(#id)")
    public TaskDto getById(@PathVariable Long id){
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id")
    @PreAuthorize("canAccessTask(#id)")
    public void deleteById(@PathVariable Long id){
        taskService.delete(id);
    }

    @PutMapping
    @Operation(summary = "Update task")
    @PreAuthorize("canAccessTask(#taskDto.id)")
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task updateTask = taskService.update(task);
        return  taskMapper.toDto(updateTask);
    }

    @PostMapping("/{id}/image")
    @Operation(summary = "Upload image to task")
    @PreAuthorize("canAccessTask(#id)")
    public void uploadImage(@PathVariable Long id,
                            @Validated @ModelAttribute TaskImageDto imageDto){
        TaskImage image = taskImageMapper.toEntity(imageDto);
        taskService.uploadImage(id, image);
    }


}
