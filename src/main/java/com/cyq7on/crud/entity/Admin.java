package com.cyq7on.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
public class Admin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String pwd;
}
