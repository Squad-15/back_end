package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.DocumentDto;
import com.jota_nunes_back_end.jotanunes.models.Documento;
import com.jota_nunes_back_end.jotanunes.repositories.DocumentRepository;
import com.jota_nunes_back_end.jotanunes.services.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping
    public List<DocumentDto> listDocuments() {
        return documentRepository.findAll().stream()
                .map(DocumentDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public DocumentDto findByIdDto(@PathVariable long id) {
        Documento documento = documentService.findById(id);
        return new DocumentDto(documento);
    }

    @PostMapping
    public ResponseEntity<Documento> createDocumentDto(@RequestBody @Valid Documento documento) {
        Documento newDocumento = documentService.create(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDocumento);
    }

    @PutMapping("/{id}")
    public Documento update(@PathVariable long id, @RequestBody Documento documento) { return documentService.update(id, documento);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable long id) {
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
