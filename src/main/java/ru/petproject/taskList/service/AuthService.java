package ru.petproject.taskList.service;

public interface AuthService {

    JwtResponce login(JwtResponce loginRequest);

    JwtResponce refresh(String refreshToken);
}
