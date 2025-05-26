package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.dtos.QuestaoDto;
import com.jota_nunes_back_end.jotanunes.models.Questao;
import com.jota_nunes_back_end.jotanunes.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    private List<QuestaoDto> showAll() {return questionRepository.findAll().stream().map(QuestaoDto::new).toList();}

    public Questao findById(Long id) {
        return (Questao) questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada"));
    }

    public Questao createQuestion(Questao questao) {return (Questao) questionRepository.save(questao);}

    public Questao updateQuestion(Long id, Questao questaoData) {
        Questao questao = findById(id);

        questao.setQtdPontos(questaoData.getQtdPontos());
        questao.setEnunciado(questaoData.getEnunciado());

        return questionRepository.save(questao);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
