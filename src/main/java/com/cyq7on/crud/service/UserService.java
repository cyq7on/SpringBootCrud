package com.cyq7on.crud.service;

import com.cyq7on.crud.common.vo.PageInfo;
import com.cyq7on.crud.entity.User;


public interface UserService {

    PageInfo<User> getUsers(String tel, int pageNo, int pageSize);

    User addUser(User user);

    User updateUser(User user);

    int deleteUser(int id);
}
