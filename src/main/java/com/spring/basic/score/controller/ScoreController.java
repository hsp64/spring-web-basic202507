package com.spring.basic.score.controller;


import com.spring.basic.score.entity.Score;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/scores")
@Slf4j
public class ScoreController {

    private List<Score> scoreList = new ArrayList<>();

    public ScoreController() {
        Score s1 = Score.builder()
                .id(1L)
                .name("김만복")
                .kor(23)
                .eng(23)
                .math(23)
                .total(23 + 23 + 23)
                .build();

        scoreList.add(s1);
    }

    @GetMapping("/score-page")
    public String scorePage(Model model) {
        model.addAttribute("scoreList", scoreList);
        return "api/v1/scores"; // 뷰 이름
    }
}

