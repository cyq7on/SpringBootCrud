package com.cyq7on.crud.controller;

import com.cyq7on.crud.common.constant.UserType;
import com.cyq7on.crud.common.utils.JWTUtil;
import com.cyq7on.crud.common.vo.Result;
import com.cyq7on.crud.entity.Admin;
import com.cyq7on.crud.entity.User;
import com.cyq7on.crud.service.AdminService;
import com.cyq7on.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class AdminController {

    @Autowired
    private AdminService service;
    @PostMapping("/login")
    public Result<String> login(@RequestBody Admin admin) {
        int i = service.login(admin.getName(), admin.getPwd());
        switch (i) {
            case 0:
                return Result.ok(JWTUtil.generateToken(admin.getId(), UserType.Admin.getType()),"登录成功");
            case 1:
                return Result.fail("用户名错误");
            case 2:
                return Result.fail("密码错误");
            default:
                return Result.fail();
        }
    }

}
