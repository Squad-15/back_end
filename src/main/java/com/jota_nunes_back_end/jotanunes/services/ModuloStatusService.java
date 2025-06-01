package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.dtos.ModuloStatusUsuarioDto;
import com.jota_nunes_back_end.jotanunes.enums.StatusProgresso;
import com.jota_nunes_back_end.jotanunes.models.Modulo;
import com.jota_nunes_back_end.jotanunes.models.StatusModuloUsuario;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.ModuloRepository;
import com.jota_nunes_back_end.jotanunes.repositories.ModuloStatusRepository;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ModuloStatusService {
    @Autowired
    private ModuloStatusRepository moduloStatusRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    // Listar todos os módulos de um usuário
    public List<ModuloStatusUsuarioDto> showAll(Long userId) {
        userAccountRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("Usuário não encontrado com id: " + userId)
        );

        List<StatusModuloUsuario> list = moduloStatusRepository.findByUserAccountId(userId);
        return list.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // Detalhar um módulo específico de um usuário
    public ModuloStatusUsuarioDto ListPerUser(Long moduloId, Long userId) {
        StatusModuloUsuario ms = moduloStatusRepository
                .findByModuloIdAndUserAccountId(moduloId, userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Nenhum status encontrado para usuário " + userId + " e módulo " + moduloId)
                );
        return toResponseDto(ms);
    }

    // Criar (registrar) um novo status de módulo para um usuário
    @Transactional
    public ModuloStatusUsuarioDto create(ModuloStatusUsuarioDto moduloStatusUsuarioDto) {
        UserAccount userAccount = userAccountRepository.findById(moduloStatusUsuarioDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com id: " + moduloStatusUsuarioDto.getUserId()));
        Modulo modulo = moduloRepository.findById(Long.valueOf(moduloStatusUsuarioDto.getModuloId()))
                .orElseThrow(() -> new IllegalArgumentException("Módulo não encontrado: " + moduloStatusUsuarioDto.getModuloId()));

        // Opcional: verificar se já existe um registro para aquele usuário+modulo
        Optional<StatusModuloUsuario> existente =
                moduloStatusRepository.findByModuloIdAndUserAccountId(Long.valueOf(modulo.getId()), userAccount.getId());
        if (existente.isPresent()) {
            throw new IllegalStateException("Status já existe. Use PUT para atualizar.");
        }

        StatusModuloUsuario novo = new StatusModuloUsuario();
        novo.setUserAccount(userAccount);
        novo.setModulo(modulo);

        // Converter status string em enum
        novo.setStatus(StatusProgresso.valueOf(String.valueOf(StatusProgresso.valueOf(moduloStatusUsuarioDto.getStatus()))));
        novo.setPercentualConcluido(moduloStatusUsuarioDto.getPercentage());
        novo.setDataConclusao(LocalDateTime.now());

        StatusModuloUsuario salvo = moduloStatusRepository.save(novo);
        return toResponseDto(salvo);
    }

    @Transactional
    public ModuloStatusUsuarioDto atualizarStatus(Long id, ModuloStatusUsuarioDto dto) {
        StatusModuloUsuario existente = moduloStatusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Status não encontrado com id: " + id));

        if (dto.getStatus() != null) {
            existente.setStatus(StatusProgresso.valueOf(dto.getStatus()));
        }

        if (dto.getPercentage() != null) {
            existente.setPercentualConcluido(dto.getPercentage());
        }

        existente.setDataConclusao(LocalDateTime.now());

        StatusModuloUsuario atualizado = moduloStatusRepository.save(existente);
        return toResponseDto(atualizado);
    }

    // Remover um status (DELETE)
    @Transactional
    public void removerStatus(Long id) {
        StatusModuloUsuario existente = moduloStatusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Status não encontrado com id: " + id));
        moduloStatusRepository.delete(existente);
    }

    // Mapeia de entidade para DTO de resposta
    private ModuloStatusUsuarioDto toResponseDto(StatusModuloUsuario ms) {
        return ModuloStatusUsuarioDto.builder()
                .idStatus(Long.valueOf(ms.getId()))
                .userId(ms.getUserAccount().getId())
                .moduloId(ms.getModulo().getId())
                .status(String.valueOf(ms.getStatus()))
                .percentage(ms.getPercentualConcluido())
                .build();
    }

}
