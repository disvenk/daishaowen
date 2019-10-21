package com.daishaowen.test.serviceImpl;

import com.daishaowen.test.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    TransactionMapper mapper;

    @Transactional
    public void get() throws InterruptedException {
        int a =9;
        mapper.getNginx();
        int b=9;
    }

    public void getNginx2(){
        mapper.getNginx2();
    }
}
