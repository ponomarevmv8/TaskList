package ru.petproject.taskList.mapper;

import org.mapstruct.Mapper;
import ru.petproject.taskList.dto.task.TaskDto;
import ru.petproject.taskList.entity.task.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {
}
