package com.spring.basic.score.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ScoreResponse {

    private Long id; // 학번
    private String maskingName; // 이름 마스킹 처리 (ex. 김*복)
    private int sum; // 총점
    private double avg; // 평균
    private int rank; // 석차 - 추후 확장용
}
