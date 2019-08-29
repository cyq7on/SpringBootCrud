package com.cyq7on.crud.dao;

import com.cyq7on.crud.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    @Select("select * from user where tel LIKE CONCAT('%', ${tel}, '%')")
    List<User> getUser(String tel);

    @Insert("insert into user(userName,avatar,age,tel) values (#{userName},#{avatar},#{age},#{tel})")
    User addUser(User user);

    @Update("update user set userName=#{userName},avatar=#{avatar},age=#{age},tel = #{tel} where id=#{id}")
    User updateUser(User user);

    @Delete("delete from user where id=#{id}")
    User deleteUser(int id);

}
