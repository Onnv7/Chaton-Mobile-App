package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.entity.Test;
import com.onnv.ChatAPI.entity.Test1;
import com.onnv.ChatAPI.entity.User;
import com.onnv.ChatAPI.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestRepository testRepository;


    @Autowired
    private UserService userService;

    @Override
    public boolean savedb(Test test) {
        testRepository.save(test);
        return true;
    }
}
