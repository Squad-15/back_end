package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.TrilhaQuestionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrilhaQuestionarioRepository extends JpaRepository<TrilhaQuestionario, Long> {
    List<TrilhaQuestionario> findByTrilha_Id(Long trilhaId);
    Optional<TrilhaQuestionario> findByQuestionario_IdAndTrilha_Id(Long id, Long questionarioId);
}
