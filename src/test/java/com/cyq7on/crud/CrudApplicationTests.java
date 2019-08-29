package com.cyq7on.crud;

import com.cyq7on.crud.dao.UserMapper;
import com.cyq7on.crud.entity.User;
import com.cyq7on.crud.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService service;
    @Test
    public void contextLoads() {
    }

}
