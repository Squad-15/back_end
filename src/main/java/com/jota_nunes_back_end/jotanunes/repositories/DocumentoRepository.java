package com.jota_nunes_back_end.jotanunes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jota_nunes_back_end.jotanunes.models.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

}
