package com.daishaowen.test.rabbitmqModel;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by disvenk.dai on 2018-08-22 15:38
 */
@Data
public class User implements Serializable{
    private String id;
    private String name;
    private Integer age;

    public User(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
