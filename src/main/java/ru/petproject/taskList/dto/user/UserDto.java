package ru.petproject.taskList.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.petproject.taskList.dto.validation.OnCreate;
import ru.petproject.taskList.dto.validation.OnUpdate;

@Data
public class UserDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Name length must be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @NotNull(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username length must be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @NotNull(message = "Password must be not null", groups = {OnCreate.class, OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Password must be not null", groups = {OnCreate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;

}
