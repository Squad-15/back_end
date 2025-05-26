package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.TrilhaDto;
import com.jota_nunes_back_end.jotanunes.models.Trilha;
import com.jota_nunes_back_end.jotanunes.repositories.TrilhaRepository;
import com.jota_nunes_back_end.jotanunes.services.TrilhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/path")
public class TrilhaController {
    @Autowired
    private TrilhaService trilhaService;
    @Autowired
    private TrilhaRepository trilhaRepository;

    @GetMapping
    public List<TrilhaDto> showAllDTO() {
        return trilhaRepository.findAll().stream().map(TrilhaDto::new).toList();
    }


    @GetMapping("/{id}")
    public TrilhaDto findByIdDTO(@PathVariable int id) {
        Trilha trilha = trilhaService.findById((long) id);
        return new TrilhaDto(trilha);
    }

    @PostMapping
    public ResponseEntity<Trilha> create(@RequestBody Trilha trilha) {
        Trilha newTrilha = trilhaService.create(trilha);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTrilha);
    }

    @PutMapping("/{id}")
    public Trilha update(@PathVariable Long id, @RequestBody Trilha trilha) {
        return trilhaService.update(id, trilha);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trilhaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
