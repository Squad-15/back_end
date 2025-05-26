package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.QuestaoDto;
import com.jota_nunes_back_end.jotanunes.models.Questao;
import com.jota_nunes_back_end.jotanunes.repositories.QuestionRepository;
import com.jota_nunes_back_end.jotanunes.services.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public List<QuestaoDto> listAllQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestaoDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public QuestaoDto findById(@PathVariable int id) {
        Questao questao = questionService.findById((long) id);
        return new QuestaoDto(questao);
    }

    @PostMapping
    public ResponseEntity<Questao> create(@RequestBody @Valid Questao questao) {
        Questao newQuestao = questionService.createQuestion(questao);
        return ResponseEntity.status(HttpStatus.CREATED).body(newQuestao);
    }

    @PutMapping("/{id}")
    public Questao update(@PathVariable int id, @RequestBody @Valid Questao questao) {return questionService.updateQuestion((long) id, questao);}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        questionService.deleteQuestion((long) id);
        return ResponseEntity.noContent().build();
    }
}
