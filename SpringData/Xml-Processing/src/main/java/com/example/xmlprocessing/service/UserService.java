package com.example.xmlprocessing.service;

import com.example.xmlprocessing.models.dtos.UserSeedDto;
import com.example.xmlprocessing.models.entity.User;

import java.util.List;

public interface UserService {

    void seedUsers(List<UserSeedDto> users);

    long getCount();

    User getRandomUser();
}
