package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.ModuloStatusUsuarioDto;
import com.jota_nunes_back_end.jotanunes.services.ModuloStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulos-status")
public class ModuloStatusController {
    @Autowired
    private ModuloStatusService moduloStatusService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ModuloStatusUsuarioDto>> listPerUser(
            @PathVariable Long userId
    ) {
        List<ModuloStatusUsuarioDto> lista = moduloStatusService.showAll(userId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/modulo/{moduloId}/user/{userId}")
    public ResponseEntity<ModuloStatusUsuarioDto> detalharStatus(
            @PathVariable Long moduloId,
            @PathVariable Long userId
    ) {
        ModuloStatusUsuarioDto dto = moduloStatusService.ListPerUser(moduloId, userId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ModuloStatusUsuarioDto> criarStatus(
            @RequestBody ModuloStatusUsuarioDto requestDto
    ) {
        ModuloStatusUsuarioDto criado = moduloStatusService.create(requestDto);
        return ResponseEntity
                .status(201)
                .body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuloStatusUsuarioDto> atualizarStatus(
            @PathVariable Long id,
            @RequestBody ModuloStatusUsuarioDto requestDto
    ) {
        ModuloStatusUsuarioDto atualizado = moduloStatusService.atualizarStatus(id, requestDto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerStatus(
            @PathVariable Long id
    ) {
        moduloStatusService.removerStatus(id);
        return ResponseEntity.noContent().build();
    }
}
