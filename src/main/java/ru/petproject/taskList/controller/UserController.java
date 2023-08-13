package ru.petproject.taskList.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.petproject.taskList.dto.task.TaskDto;
import ru.petproject.taskList.dto.user.UserDto;
import ru.petproject.taskList.dto.validation.OnCreate;
import ru.petproject.taskList.dto.validation.OnUpdate;
import ru.petproject.taskList.entity.task.Task;
import ru.petproject.taskList.entity.user.User;
import ru.petproject.taskList.mapper.TaskMapper;
import ru.petproject.taskList.mapper.UserMapper;
import ru.petproject.taskList.service.TaskService;
import ru.petproject.taskList.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User controller", description = "User API")
public class UserController {

    private  final UserService userService;
    private final TaskService taskService;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userDto.id)")
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto){
        User user = userMapper.toEntity(userDto);
        User updateUser = userService.update(user);
        return userMapper.toDto(updateUser);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get userDto by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable Long id){
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete userDto by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get all tasks by user id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id){
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Create task for user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public TaskDto createTask(@PathVariable Long id,
                              @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }
}
