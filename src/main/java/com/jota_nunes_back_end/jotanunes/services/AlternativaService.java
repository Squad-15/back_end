package com.jota_nunes_back_end.jotanunes.services;


import com.jota_nunes_back_end.jotanunes.dtos.AlternativaDto;
import com.jota_nunes_back_end.jotanunes.models.Alternativas;
import com.jota_nunes_back_end.jotanunes.repositories.AlternativasRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlternativaService {
    @Autowired
    private AlternativasRepositiory alternativasRepositiory;

    private List<AlternativaDto> showAllQuestionario() {return alternativasRepositiory.findAll().stream().map(AlternativaDto::new).toList();}

    public Alternativas findById(Long id) {
        return (Alternativas) alternativasRepositiory.findById(id)
                .orElseThrow(() -> new RuntimeException("Alternativa não encontrada"));
    }

    public Alternativas createAlternativa(Alternativas alternativas) {return (Alternativas) alternativasRepositiory.save(alternativas);}

    public Alternativas updateAlternativa(Long id, Alternativas alternativasData) {
        Alternativas alternativas = findById(id);
        alternativas.setTexto(alternativasData.getTexto());
        alternativas.setCorreta(alternativasData.getCorreta());
        return alternativasRepositiory.save(alternativas);
    }

    public void deleteAlternativa(Long id) {
        alternativasRepositiory.deleteById(id);
    }
}
