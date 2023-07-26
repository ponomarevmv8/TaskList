package ru.petproject.taskList.service.Impl;

import lombok.Data;
import ru.petproject.taskList.dto.auth.JwtRequest;
import ru.petproject.taskList.dto.auth.JwtResponse;
import ru.petproject.taskList.service.AuthService;


@Data
public class AuthServiceImpl implements AuthService {


    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
