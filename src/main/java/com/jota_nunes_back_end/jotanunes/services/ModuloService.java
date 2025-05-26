package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.dtos.ModuloDto;
import com.jota_nunes_back_end.jotanunes.models.Modulo;
import com.jota_nunes_back_end.jotanunes.repositories.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    public List<ModuloDto> showAll() {return moduloRepository.findAll().stream().map(ModuloDto::new).toList();}

    public Modulo findById(Long id) {
        return (Modulo) moduloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modulo não encontrado"));
    }

    public Modulo create(Modulo modulo) { return (Modulo) moduloRepository.save(modulo);}

    public Modulo update(Long id, Modulo moduloData) {
        Modulo modulo = findById(id);

        modulo.setName(moduloData.getName());
        modulo.setDescription(moduloData.getDescription());

        return moduloRepository.save(modulo);
    }

    public void delete(Long id) { moduloRepository.deleteById(id); }
}
