package com.cyq7on.crud.service.impl;

import com.cyq7on.crud.common.vo.PageInfo;
import com.cyq7on.crud.dao.UserMapper;
import com.cyq7on.crud.entity.Admin;
import com.cyq7on.crud.entity.User;
import com.cyq7on.crud.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public PageInfo<User> getUsers(String tel, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> list;
        if (StringUtils.isEmpty(tel)) {
            list =  mapper.getUsers();
        } else {
            list = mapper.getUser(tel);
        }
        return PageInfo.data(list);
    }


    @Override
    public User addUser(User user) {
        mapper.addUser(user);
        return user;

    }

    @Override
    public User updateUser(User user) {
        User userById = mapper.getUserById(user.getId());
        if (userById == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!StringUtils.isEmpty(user.getUserName())){
            userById.setUserName(user.getUserName());
        }
        if (!StringUtils.isEmpty(user.getAvatar())){
            userById.setAvatar(user.getAvatar());
        }
        if (!StringUtils.isEmpty(user.getAge())){
            userById.setAge(user.getAge());
        }
        if (!StringUtils.isEmpty(user.getTel())){
            userById.setTel(user.getTel());
        }
        mapper.updateUser(userById);
        return userById;
    }

    @Override
    public int deleteUser(int id) {
        return mapper.deleteUser(id);
    }
}
