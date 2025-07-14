package com.spring.basic.chap2_3.exmaple.service;

import com.spring.basic.chap2_3.exmaple.model.MyModel;
import com.spring.basic.chap2_3.exmaple.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public String processData() {
        MyModel model = myRepository.findData();
        return "Processed: " + model.getData();
    }
}