package ru.petproject.taskList.service;

import ru.petproject.taskList.dto.auth.JwtRequest;
import ru.petproject.taskList.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
