package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.Questao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questao, Long> {}
