package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.QuestionarioDto;
import com.jota_nunes_back_end.jotanunes.models.Questionario;
import com.jota_nunes_back_end.jotanunes.repositories.QuestionarioRepository;
import com.jota_nunes_back_end.jotanunes.services.QuestionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionario")
public class QuestionarioController {
    @Autowired
    private QuestionarioService questionarioService;

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @GetMapping
    public List<QuestionarioDto> listAllQuestionarios() {
        return questionarioRepository.findAll().stream()
                .map(QuestionarioDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public QuestionarioDto findById(@PathVariable int id) {
        Questionario questionario = questionarioService.findById((long) id);
        return new QuestionarioDto(questionario);
    }

    @PostMapping
    public ResponseEntity<Questionario> createQuestionario(@RequestBody @Valid Questionario questionario) {
        Questionario newQuestionario = questionarioService.createQuestionario(questionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(newQuestionario);
    }

    @PutMapping("/{id}")
    public Questionario updateQuestionario(@PathVariable int id, @RequestBody @Valid Questionario questionario) {return questionarioService.updateQuestionario((long) id, questionario);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionario(@PathVariable int id) {
        questionarioService.deleteQuestionario((long) id);
        return ResponseEntity.noContent().build();
    }

}
