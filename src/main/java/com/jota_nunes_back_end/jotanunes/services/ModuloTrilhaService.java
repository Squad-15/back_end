package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.models.TrilhaModulo;
import com.jota_nunes_back_end.jotanunes.repositories.ModuloTrilhaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloTrilhaService {
    @Autowired
    private ModuloTrilhaRepository moduloTrilhaRepository;

    public ModuloTrilhaService(ModuloTrilhaRepository moduloTrilhaRepository) {
        this.moduloTrilhaRepository = moduloTrilhaRepository;
    }


    @Transactional
    public TrilhaModulo associateModuloToTrilha(TrilhaModulo trilhaModulo) {
        return moduloTrilhaRepository.save(trilhaModulo);
    }

    @Transactional
    public void deleteAssociantion(Long moduloTrilhaId) {
        moduloTrilhaRepository.deleteById(moduloTrilhaId);
    }

    @Transactional
    public List<TrilhaModulo> showPerModulo(Long moduloId) {
        return moduloTrilhaRepository.findByTrilhaIdOrderByOrdemAsc(moduloId);
    }
}
