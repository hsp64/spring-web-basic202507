package com.spring.basic.chap3_2.controller;


import com.spring.basic.chap3_2.entity.Practice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/3-2/practice")
public class Practice3_2 {

    private Map<String, Practice> practice = new HashMap<>();

    public Practice3_2() {

        Practice practice1 = Practice.builder()
                .userId(1)
                .message("The application is user-friendly!")
                .rating(5)
                .build();
    }
}
