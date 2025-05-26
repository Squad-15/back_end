package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.dtos.TrilhaDto;
import com.jota_nunes_back_end.jotanunes.models.Trilha;
import com.jota_nunes_back_end.jotanunes.repositories.TrilhaRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaService {

    @Autowired
    private TrilhaRepository trilhaRepository;

    public List<TrilhaDto> showAllDTO() {
        return trilhaRepository.findAll().stream().map(TrilhaDto::new).toList();
    }


    @SneakyThrows
    public Trilha findById(Long id) {
        return (Trilha) trilhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trilha não encontrada com ID " + id));
    }

    public Trilha create(Trilha trilha) {
        return (Trilha) trilhaRepository .save(trilha);
    }

    public Trilha update(Long id, Trilha trilhaData) {
        Trilha trilha = findById(id);

        trilha.setName(trilhaData.getName());
        trilha.setDescription(trilhaData.getDescription());

        return trilhaRepository.save(trilha);
    }


    public void delete(Long id) {
        trilhaRepository.deleteById(id);
    }

}
