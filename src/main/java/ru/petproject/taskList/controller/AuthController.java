package ru.petproject.taskList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.petproject.taskList.dto.auth.JwtRequest;
import ru.petproject.taskList.dto.auth.JwtResponse;
import ru.petproject.taskList.dto.user.UserDto;
import ru.petproject.taskList.dto.validation.OnCreate;
import ru.petproject.taskList.entity.user.User;
import ru.petproject.taskList.mapper.UserMapper;
import ru.petproject.taskList.service.AuthService;
import ru.petproject.taskList.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto){
        User user = userMapper.UserDtoToEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.UserToDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
