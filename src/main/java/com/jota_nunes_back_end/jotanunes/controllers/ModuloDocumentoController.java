package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.ModuloDocumentoDto;
import com.jota_nunes_back_end.jotanunes.dtos.ModuloTrilhaDto;
import com.jota_nunes_back_end.jotanunes.models.Documento;
import com.jota_nunes_back_end.jotanunes.models.Modulo;
import com.jota_nunes_back_end.jotanunes.models.ModuloDocumento;
import com.jota_nunes_back_end.jotanunes.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modulos/{moduloId}/documentos")
public class ModuloDocumentoController {
    private final ModuloRepository moduloRepository;
    private final DocumentoRepository documentoRepository;
    private final ModuloDocumentoRepository moduloDocumentoRepository;

    public ModuloDocumentoController(ModuloRepository moduloRepository,
                                     DocumentoRepository documentoRepository,
                                     ModuloDocumentoRepository moduloDocumentoRepository) {
        this.moduloRepository = moduloRepository;
        this.documentoRepository = documentoRepository;
        this.moduloDocumentoRepository = moduloDocumentoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ModuloDocumentoDto>> getModuloDocumentos(@PathVariable Long moduloId) {
        List<ModuloDocumento> moduloDocumentos = moduloDocumentoRepository.findByModulo_Id(moduloId);
        List<ModuloDocumentoDto> dtos = moduloDocumentos.stream()
                .map(ModuloDocumentoDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{documentoId}")
    public ResponseEntity<String> associateDocumento(@PathVariable Long moduloId, @PathVariable Long documentoId) {
        Optional<Modulo> moduloOpt = moduloRepository.findById(moduloId);
        Optional<Documento> documentoOpt = documentoRepository.findById(Math.toIntExact(documentoId));

        if (moduloOpt.isEmpty() || documentoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ModuloDocumento moduloDocumento = new ModuloDocumento();
        moduloDocumento.setModulo(moduloOpt.get());
        moduloDocumento.setDocumento(documentoOpt.get());
        moduloDocumentoRepository.save(moduloDocumento);

        return ResponseEntity.ok("Documento associado ao Módulo com sucesso.");
    }

    @DeleteMapping("/{documentoId}")
    public ResponseEntity<String> deleteDocumento(@PathVariable Long moduloId, @PathVariable Long documentoId) {
        Optional<ModuloDocumento> moduloDocumento =
                moduloDocumentoRepository.findByDocumento_IdAndModulo_Id(documentoId, moduloId);

        if (moduloDocumento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        moduloDocumentoRepository.delete(moduloDocumento.get());
        return ResponseEntity.ok("Documento removido com sucesso.");
    }
}

