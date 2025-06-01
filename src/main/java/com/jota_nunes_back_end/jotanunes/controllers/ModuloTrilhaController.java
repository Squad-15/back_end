package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.ModuloTrilhaDto;
import com.jota_nunes_back_end.jotanunes.models.Modulo;
import com.jota_nunes_back_end.jotanunes.models.Trilha;
import com.jota_nunes_back_end.jotanunes.models.TrilhaModulo;
import com.jota_nunes_back_end.jotanunes.repositories.ModuloRepository;
import com.jota_nunes_back_end.jotanunes.repositories.TrilhaRepository;
import com.jota_nunes_back_end.jotanunes.repositories.ModuloTrilhaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trilhas/{trilhaId}/modulos")
public class ModuloTrilhaController {

    private final ModuloRepository moduloRepository;
    private final TrilhaRepository trilhaRepository;
    private final ModuloTrilhaRepository moduloTrilhaRepository;

    public ModuloTrilhaController(ModuloRepository moduloRepository,
                                  TrilhaRepository trilhaRepository,
                                  ModuloTrilhaRepository moduloTrilhaRepository) {
        this.moduloRepository = moduloRepository;
        this.trilhaRepository = trilhaRepository;
        this.moduloTrilhaRepository = moduloTrilhaRepository;
    }

    @GetMapping
    public ResponseEntity<List<ModuloTrilhaDto>> getModulosByTrilha(@PathVariable Long trilhaId) {
        List<TrilhaModulo> modulosTrilha = moduloTrilhaRepository.findByTrilhaIdOrderByOrdemAsc(trilhaId);
        List<ModuloTrilhaDto> dtos = modulosTrilha.stream()
                .map(ModuloTrilhaDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{moduloId}")
    public ResponseEntity<String> associateModuloToTrilha(@PathVariable Long trilhaId, @PathVariable Long moduloId) {
        Optional<Trilha> trilhaOpt = trilhaRepository.findById(trilhaId);
        Optional<Modulo> moduloOpt = moduloRepository.findById(moduloId);

        if (trilhaOpt.isEmpty() || moduloOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Recupera a ordem atual máxima
        Integer maxOrdem = moduloTrilhaRepository.findMaxOrdemByTrilhaId(trilhaId);
        if (maxOrdem == null) {
            maxOrdem = 0;
        }

        TrilhaModulo moduloTrilha = new TrilhaModulo();
        moduloTrilha.setTrilha(trilhaOpt.get());
        moduloTrilha.setModulo(moduloOpt.get());
        moduloTrilha.setOrdem(maxOrdem + 1); // Define a ordem automaticamente

        moduloTrilhaRepository.save(moduloTrilha);

        return ResponseEntity.ok("Módulo associado à trilha com sucesso.");
    }

    @DeleteMapping("/{moduloId}")
    public ResponseEntity<String> deleteModulo(@PathVariable Long trilhaId, @PathVariable Long moduloId) {
        Optional<TrilhaModulo> trilhaModulo = moduloTrilhaRepository.findByTrilhaIdAndModuloId(trilhaId, moduloId);

        if (trilhaModulo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        moduloTrilhaRepository.delete(trilhaModulo.get());
        return ResponseEntity.ok("Módulo removido com sucesso.");
    }
}
