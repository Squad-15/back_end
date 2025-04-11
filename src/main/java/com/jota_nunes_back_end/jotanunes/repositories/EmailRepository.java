package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {}
