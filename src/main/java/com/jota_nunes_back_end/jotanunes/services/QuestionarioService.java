package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.dtos.QuestionarioDto;
import com.jota_nunes_back_end.jotanunes.models.Questionario;
import com.jota_nunes_back_end.jotanunes.repositories.QuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionarioService {
    @Autowired
    private QuestionarioRepository questionarioRepository;

    private List<QuestionarioDto> ShowAllQuestionarios() {return questionarioRepository.findAll().stream().map(QuestionarioDto::new).toList();}

    public Questionario findById(Long id) {
        return (Questionario) questionarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Questionario não encontrado"));
    }

    public Questionario createQuestionario(Questionario questionario) {return (Questionario) questionarioRepository.save(questionario);}

    public Questionario updateQuestionario(Long id, Questionario questionarioData) {
        Questionario questionario = findById(id);

        questionario.setType(questionarioData.getType());

        return questionarioRepository.save(questionario);
    }

    public void deleteQuestionario(Long id) {
        questionarioRepository.deleteById(id);
    }
}
