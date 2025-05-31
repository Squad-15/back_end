package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.dtos.DocumentDto;
import com.jota_nunes_back_end.jotanunes.models.Documento;
import com.jota_nunes_back_end.jotanunes.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    private List<DocumentDto> showAll() { return documentRepository.findAll().stream().map(DocumentDto::new).toList();}

    public Documento findById(Long id) {
        return (Documento) documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));
    }

    public Documento create(Documento documento) {return (Documento) documentRepository.save(documento);}

    public Documento update(Long id, Documento documentoData) {
        Documento documento = findById(id);

        documento.setTitulo(documentoData.getTitulo());
        documento.setDescription(documentoData.getDescription());
        documento.setUrlVideo(documentoData.getUrlVideo());
        documento.setUrlPhoto(documentoData.getUrlPhoto());
        documento.setOrdem(documentoData.getOrdem());
        documento.setTipo(documentoData.getTipo());
        return documentRepository.save(documento);
    }

    public void delete(Long id) {documentRepository.deleteById(id);}
}
