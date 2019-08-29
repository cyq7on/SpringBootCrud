package com.cyq7on.crud.service;

import com.cyq7on.crud.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers(String tel);

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(int id);
}
