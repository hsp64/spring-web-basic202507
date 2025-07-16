package com.spring.basic.score.controller;

import com.spring.basic.score.dto.ScoreResponse;
import com.spring.basic.score.entity.Score;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/scores")
@Slf4j
public class ScoreController {

    private final List<Score> scoreList = new ArrayList<>();
    private long sequence = 1L;

    public ScoreController() {
        Score s1 = Score.builder()
                .id(sequence++)
                .name("김만복")
                .kor(90)
                .eng(80)
                .math(70)
                .total(90 + 80 + 70)
                .average((90 + 80 + 70) / 3.0)
                .build();
        scoreList.add(s1);
    }

    // 페이지 요청
    @GetMapping("/score-page")
    public String scorePage(Model model) {
        model.addAttribute("title", "Score");
        return "score/score-page"; // => /WEB-INF/views/api/v1/scores.jsp
    }

    // 전체 목록 JSON 응답
    @GetMapping
    @ResponseBody
    public List<ScoreResponse> getScores(@RequestParam(defaultValue = "id") String sort) {
        List<Score> sorted = sortScoreList(scoreList, sort);

        return sorted.stream()
                .map(score -> ScoreResponse.builder()
                        .id(score.getId())
                        .maskingName(maskName(score.getName()))
                        .sum(score.getTotal())
                        .avg(score.getAverage())
                        .rank(1) // 현재는 랭크 계산 X
                        .build())
                .collect(Collectors.toList());
    }

    // 성적 등록
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createScore(@RequestBody Map<String, String> payload) {
        try {
            String name = payload.get("studentName");
            int kor = Integer.parseInt(payload.get("korean"));
            int eng = Integer.parseInt(payload.get("english"));
            int math = Integer.parseInt(payload.get("math"));

            int total = kor + eng + math;
            double average = total / 3.0;

            Score newScore = Score.builder()
                    .id(sequence++)
                    .name(name)
                    .kor(kor)
                    .eng(eng)
                    .math(math)
                    .total(total)
                    .average(average)
                    .build();

            scoreList.add(newScore);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("studentName", "이름을 정확히 입력하세요");
            errors.put("korean", "국어 점수 오류");
            errors.put("english", "영어 점수 오류");
            errors.put("math", "수학 점수 오류");
            return ResponseEntity.badRequest().body(errors);
        }
    }

    // 성적 삭제
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteScore(@PathVariable Long id) {
        boolean removed = scoreList.removeIf(score -> score.getId().equals(id));
        return removed ? ResponseEntity.ok().build()
                : ResponseEntity.status(404).body("삭제 실패");
    }

    // 이름 마스킹
    private String maskName(String name) {
        return name.charAt(0) + "*" + name.charAt(name.length() - 1);
    }

    // 정렬
    private List<Score> sortScoreList(List<Score> list, String sort) {
        return switch (sort) {
            case "name" -> list.stream()
                    .sorted(Comparator.comparing(Score::getName)).toList();
            case "average" -> list.stream()
                    .sorted(Comparator.comparingDouble(Score::getAverage).reversed()).toList();
            default -> list.stream()
                    .sorted(Comparator.comparing(Score::getId)).toList();
        };
    }
}
