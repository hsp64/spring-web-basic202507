package com.spring.basic.chap2_3.exmaple.controller;

import com.spring.basic.chap2_3.exmaple.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    public void handleRequest() {
        String result = myService.processData();
        System.out.println("Controller Output: " + result);
    }
}