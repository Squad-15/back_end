package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.models.ModuloDocumento;
import com.jota_nunes_back_end.jotanunes.repositories.ModuloDocumentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloDocumentoService {
    @Autowired
    private ModuloDocumentoRepository moduloDocumentoRepository;

    public ModuloDocumentoService(ModuloDocumentoRepository moduloDocumentoRepository) {
        this.moduloDocumentoRepository = moduloDocumentoRepository;
    }

    @Transactional
    public ModuloDocumento associateModuloDocumento(ModuloDocumento moduloDocumento) {
        return moduloDocumentoRepository.save(moduloDocumento);
    }

    @Transactional
    public void deleteAssociation(Long moduloDocumentoId) {
        moduloDocumentoRepository.deleteById(moduloDocumentoId);

    }

    @Transactional
    public List<ModuloDocumento> showPerModulo(Long moduloId) {
        return moduloDocumentoRepository.findByModulo_Id(moduloId);
    }
}
