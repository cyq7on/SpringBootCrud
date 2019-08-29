package com.cyq7on.crud.service;

import com.cyq7on.crud.entity.User;

import java.util.List;

public interface UserService {

    int login(String name, String pwd);

    List<User> getUsers(String tel);

    User addUser(User user);

    User updateUser(User user);

    int deleteUser(int id);
}
