package com.example.springdto.dtoexercise.service;

import com.example.springdto.dtoexercise.model.dto.UserLoginDto;
import com.example.springdto.dtoexercise.model.dto.UserRegisterDto;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();
}
