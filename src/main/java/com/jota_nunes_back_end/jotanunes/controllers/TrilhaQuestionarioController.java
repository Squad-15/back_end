package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.TrilhaQuestionarioDto;
import com.jota_nunes_back_end.jotanunes.models.Questionario;
import com.jota_nunes_back_end.jotanunes.models.Trilha;
import com.jota_nunes_back_end.jotanunes.models.TrilhaQuestionario;
import com.jota_nunes_back_end.jotanunes.repositories.QuestionarioRepository;
import com.jota_nunes_back_end.jotanunes.repositories.TrilhaQuestionarioRepository;
import com.jota_nunes_back_end.jotanunes.repositories.TrilhaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trilhas/{trilhaId}/questionarios")
public class TrilhaQuestionarioController {

    private final TrilhaRepository trilhaRepository;
    private final QuestionarioRepository questionarioRepository;
    private final TrilhaQuestionarioRepository trilhaQuestionarioRepository;

    public TrilhaQuestionarioController(TrilhaRepository trilhaRepository,
                                        QuestionarioRepository questionarioRepository,
                                        TrilhaQuestionarioRepository trilhaQuestionarioRepository) {
        this.trilhaRepository = trilhaRepository;
        this.questionarioRepository = questionarioRepository;
        this.trilhaQuestionarioRepository = trilhaQuestionarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<TrilhaQuestionarioDto>> getTrilhaQuestionarios(@PathVariable Long trilhaId) {
        List<TrilhaQuestionario> trilhaQuestionarios = trilhaQuestionarioRepository.findByTrilha_Id(trilhaId);
        List<TrilhaQuestionarioDto> dtos = trilhaQuestionarios.stream()
                .map(TrilhaQuestionarioDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{questionarioId}")
    public ResponseEntity<String> associateQuestionario(@PathVariable Long trilhaId, @PathVariable Long questionarioId) {
        Optional<Trilha> trilhaOpt = trilhaRepository.findById(trilhaId);
        Optional<Questionario> questionarioOpt = questionarioRepository.findById(questionarioId);

        if (trilhaOpt.isEmpty() || questionarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TrilhaQuestionario trilhaQuestionario = new TrilhaQuestionario();
        trilhaQuestionario.setTrilha(trilhaOpt.get());
        trilhaQuestionario.setQuestionario(questionarioOpt.get());
        trilhaQuestionarioRepository.save(trilhaQuestionario);

        return ResponseEntity.ok("Questionário associado à Trilha com sucesso.");
    }

    @DeleteMapping("/{questionarioId}")
    public ResponseEntity<String> deleteQuestionario(@PathVariable Long trilhaId, @PathVariable Long questionarioId) {
        Optional<TrilhaQuestionario> trilhaQuestionario =
                trilhaQuestionarioRepository.findByQuestionario_IdAndTrilha_Id(questionarioId, trilhaId);

        if (trilhaQuestionario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        trilhaQuestionarioRepository.delete(trilhaQuestionario.get());
        return ResponseEntity.ok("Questionário removido com sucesso.");
    }
}
