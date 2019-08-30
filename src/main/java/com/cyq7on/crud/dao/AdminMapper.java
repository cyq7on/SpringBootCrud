package com.cyq7on.crud.dao;

import com.cyq7on.crud.entity.Admin;
import com.cyq7on.crud.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminMapper {

    @Select("select * from admin where name=#{name}")
    Admin getAdmin(String name);

}
