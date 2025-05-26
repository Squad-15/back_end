package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.ModuloDto;
import com.jota_nunes_back_end.jotanunes.models.Modulo;
import com.jota_nunes_back_end.jotanunes.repositories.ModuloRepository;
import com.jota_nunes_back_end.jotanunes.services.ModuloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulo")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private ModuloRepository moduloRepository;

    @GetMapping
    public List<ModuloDto> listarModulos() {
        return moduloRepository.findAll().stream()
                .map(ModuloDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ModuloDto findByIdDto(@PathVariable long id) {
        Modulo modulo = moduloService.findById(id);
        return new ModuloDto(modulo);
    }

    @PostMapping()
    public ResponseEntity<Modulo> create(@RequestBody @Valid Modulo modulo) {
        Modulo newModulo = moduloService.create(modulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newModulo);
    }

    @PutMapping("/{id}")
    public Modulo update(@PathVariable long id, @RequestBody Modulo modulo) { return moduloService.update(id, modulo);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        moduloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
