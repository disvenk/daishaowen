package com.daishaowen.test.serviceImpl;

import com.daishaowen.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    UserMapper userMapper;


    public List<String> getName(){
        return userMapper.getName();
    }

    @Transactional
    public void updateName(){
        userMapper.updateName();
//        int a=1/0;
    }
}
