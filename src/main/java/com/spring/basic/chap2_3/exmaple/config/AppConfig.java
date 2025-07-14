package com.spring.basic.chap2_3.exmaple.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.basic.chap2_3")
public class AppConfig {
    // 스프링 빈 설정은 @ComponentScan으로 자동으로 설정됨
}
