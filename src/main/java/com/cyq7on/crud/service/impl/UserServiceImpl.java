package com.cyq7on.crud.service.impl;

import com.cyq7on.crud.dao.UserMapper;
import com.cyq7on.crud.entity.User;
import com.cyq7on.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getUsers(String tel) {

        if (StringUtils.isEmpty(tel)) {
            return mapper.getUsers();
        } else {
            return mapper.getUser(tel);
        }
    }

    @Override
    public User addUser(User user) {
        return mapper.addUser(user);

    }

    @Override
    public User updateUser(User user) {
        return mapper.updateUser(user);
    }

    @Override
    public User deleteUser(int id) {
        return mapper.deleteUser(id);
    }
}
