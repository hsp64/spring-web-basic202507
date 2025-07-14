package com.spring.basic.chap2_3.exmaple.repository;

import com.spring.basic.chap2_3.exmaple.model.MyModel;
import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {

    public MyModel findData() {
        // 데이터베이스에서 데이터를 가져오는 것처럼 가정
        return new MyModel("Repository Data");
    }
}
