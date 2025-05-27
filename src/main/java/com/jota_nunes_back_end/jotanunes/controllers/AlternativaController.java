package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.AlternativaDto;
import com.jota_nunes_back_end.jotanunes.models.Alternativas;
import com.jota_nunes_back_end.jotanunes.repositories.AlternativasRepositiory;
import com.jota_nunes_back_end.jotanunes.services.AlternativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alternativa")
public class AlternativaController {
    @Autowired
    private AlternativaService alternativaService;

    @Autowired
    private AlternativasRepositiory alternativasRepositiory;

    @GetMapping
    public List<AlternativaDto> listAllAlternativas() {
        return alternativasRepositiory.findAll().stream()
                .map(AlternativaDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public AlternativaDto findById(@PathVariable int id) {
        Alternativas alternativas = alternativaService.findById((long) id);
        return new AlternativaDto(alternativas);
    }

    @PostMapping
    public ResponseEntity<Alternativas> createAlternativas(@RequestBody @Valid Alternativas alternativas) {
        Alternativas newAlterntivas = alternativaService.createAlternativa(alternativas);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAlterntivas);
    }

    @PutMapping("/{id}")
    public Alternativas updateAlternativas(@PathVariable int id, @RequestBody Alternativas alternativas) {return alternativaService.updateAlternativa((long) id, alternativas);}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlternativas(@PathVariable int id) {
        alternativaService.deleteAlternativa((long) id);
        return ResponseEntity.noContent().build();
    }
}
