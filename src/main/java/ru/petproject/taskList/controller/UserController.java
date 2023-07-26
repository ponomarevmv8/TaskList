package ru.petproject.taskList.controller;

import lombok.RequiredArgsConstructor;
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
public class UserController {

    private  final UserService userService;
    private final TaskService taskService;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto){
        User user = userMapper.UserDtoToEntity(userDto);
        User updateUser = userService.update(user);
        return userMapper.UserToDto(updateUser);
    }


    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        User user = userService.getById(id);
        return userMapper.UserToDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id){
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.taskToDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable Long id,
                              @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.taskDtoToEntity(taskDto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.taskToDto(createdTask);
    }
}
