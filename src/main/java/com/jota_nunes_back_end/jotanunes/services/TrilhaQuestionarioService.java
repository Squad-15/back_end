package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.models.TrilhaQuestionario;
import com.jota_nunes_back_end.jotanunes.repositories.TrilhaQuestionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaQuestionarioService {
    @Autowired
    private TrilhaQuestionarioRepository trilhaQuestionarioRepository;

    public TrilhaQuestionarioService(TrilhaQuestionarioRepository trilhaQuestionarioRepository) {
        this.trilhaQuestionarioRepository = trilhaQuestionarioRepository;
    }

    @Transactional
    public TrilhaQuestionario associateTrilhaQuestionario(TrilhaQuestionario trilhaQuestionario) {
        return trilhaQuestionarioRepository.save(trilhaQuestionario);
    }

    @Transactional
    public void deleteAssociation(Long trilhaQuestionarioId) {
        trilhaQuestionarioRepository.deleteById(trilhaQuestionarioId);
    }

    @Transactional
    public List<TrilhaQuestionario> findPerTrilha(Long trilhaId) {
        return trilhaQuestionarioRepository.findByTrilha_Id(trilhaId);
    }
}
