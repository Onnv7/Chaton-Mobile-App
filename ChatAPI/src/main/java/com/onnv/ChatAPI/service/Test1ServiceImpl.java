package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.entity.Test;
import com.onnv.ChatAPI.entity.Test1;
import com.onnv.ChatAPI.repository.Test1Repository;
import com.onnv.ChatAPI.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Test1ServiceImpl implements Test1Service {

    @Autowired
    Test1Repository test1Repository;


    @Autowired
    TestRepository testRepository;
    @Override
    public boolean saveDB(String id) {
//        Test t = new Test();
//        t.setId(id);
        Optional<Test> t = testRepository.findById(id);
        if(t.isPresent()) {

            t.get().setDes("OMGGG");
            Test1 t1 = new Test1();
            t1.setTest(t.get());
            test1Repository.save(t1);
            return true;
        }
//        testRepository.save(new Test().builder().user(friendInfo).des("OKOK").build());
        return false;
    }
}
