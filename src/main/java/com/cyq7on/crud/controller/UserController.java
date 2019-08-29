package com.cyq7on.crud.controller;

import com.cyq7on.crud.common.vo.Result;
import com.cyq7on.crud.entity.User;
import com.cyq7on.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/list")
    public Result<List<User>> getUsers(@RequestParam(value = "tel", required = false) String tel) {
        return Result.ok(service.getUsers(tel));
    }

    @PostMapping("/add")
    public Result<User> addUser(@RequestBody User user){
        return Result.ok(service.addUser(user));
    }

    @PostMapping("/update")
    public Result<User> updateUser(@RequestBody User user){
        return Result.ok(service.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(@PathVariable(value = "id") int id){
        int i = service.deleteUser(id);
        if (i > 0) {
            return Result.ok("删除用户成功");
        }else {
            return Result.fail("用户不存在");
        }
    }

}
