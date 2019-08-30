package com.cyq7on.crud.service.impl;

import com.cyq7on.crud.dao.AdminMapper;
import com.cyq7on.crud.dao.UserMapper;
import com.cyq7on.crud.entity.Admin;
import com.cyq7on.crud.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;

    @Override
    public int login(String name, String pwd) {
        Admin admin = mapper.getAdmin(name);
        if (admin == null) {
            return 1;
        }
        if (admin.getPwd().equals(pwd)) {
            return 0;
        }
        return 2;
    }

}
