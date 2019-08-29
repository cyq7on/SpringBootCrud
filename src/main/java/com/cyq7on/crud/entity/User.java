package com.cyq7on.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String avatar;
    private int age;
    private String tel;
}
