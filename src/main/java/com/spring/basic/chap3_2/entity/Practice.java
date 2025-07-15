package com.spring.basic.chap3_2.entity;


import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Practice {

//    @Builder.Default
    private int userId;
    private String message;
    private int rating;


}
