package ru.petproject.taskList.mapper;

import org.mapstruct.Mapper;
import ru.petproject.taskList.dto.task.TaskDto;
import ru.petproject.taskList.entity.task.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto taskToDto(Task task);

    List<TaskDto> taskToDto(List<Task> tasks);

    Task taskDtoToEntity(TaskDto taskDto);

}
