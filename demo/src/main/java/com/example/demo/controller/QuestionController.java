package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.QuestionDto;
import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping
    public Question createQuestion(@AuthenticationPrincipal UserDetails userDetails,

            @Valid @RequestBody QuestionDto questionDto) {
        Question question = new Question();
        question.setDescription(questionDto.getDescription());
        question.setAnswer(questionDto.getAnswer());
        question.setExplanation(questionDto.getExplanation());
        question.setOptions(questionDto.getOptions());

        return questionService.createQuestion(userDetails.getUsername(), question);
    }

    @PatchMapping("/{id}")
    public Question updateQuestion(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody Question question) {
        return questionService.updateQuestion(id, userDetails.getUsername(), question);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        questionService.deleteQuestion(id, userDetails.getUsername());
        return "Question deleted successfully";
    }
}
