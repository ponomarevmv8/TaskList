package ru.petproject.taskList.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.petproject.taskList.dto.validation.OnCreate;
import ru.petproject.taskList.dto.validation.OnUpdate;

@Data
@Schema(description = "User DTO")
public class UserDto {

    @Schema(description = "user id", example = "1")
    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @Schema(description = "User name", example = "John Doe")
    @NotNull(message = "name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Name length must be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @Schema(description = "User email", example = "johndoe@gmail.com")
    @NotNull(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username length must be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @Schema(description = "User crypted password", example = "12345")
    @NotNull(message = "Password must be not null", groups = {OnCreate.class, OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "User password confirmation", example = "12345")
    @NotNull(message = "Password must be not null", groups = {OnCreate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;

}
