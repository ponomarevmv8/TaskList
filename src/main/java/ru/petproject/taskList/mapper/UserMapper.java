package ru.petproject.taskList.mapper;

import org.mapstruct.Mapper;
import ru.petproject.taskList.dto.user.UserDto;
import ru.petproject.taskList.entity.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {}
