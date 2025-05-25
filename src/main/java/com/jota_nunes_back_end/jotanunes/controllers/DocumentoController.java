package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.DocumentoDto;
import com.jota_nunes_back_end.jotanunes.services.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller para gerenciamento de documentos PDF
 */
@RestController
@RequestMapping("/documentos")
public class DocumentoController {
    
    private static final Logger logger = Logger.getLogger(DocumentoController.class.getName());
    
    @Autowired
    private DocumentoService documentoService;
    
    /**
     * Upload de um novo documento PDF
     * 
     * @param arquivo Arquivo PDF
     * @param titulo Título do documento
     * @param ordem Ordem de exibição
     * @param tipo Tipo do documento
     * @param qtdPontos Quantidade de pontos
     * @return ResponseEntity com o DTO do documento salvo
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentoDto> uploadDocumento(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("titulo") String titulo,
            @RequestParam(value = "ordem", required = false) Integer ordem,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "qtdPontos", required = false) Integer qtdPontos) {
        
        try {
            logger.info("Recebida solicitação para upload de documento: " + titulo);
            DocumentoDto documentoSalvo = documentoService.salvarDocumento(titulo, arquivo, ordem, tipo, qtdPontos);
            return new ResponseEntity<>(documentoSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.warning("Erro de validação ao fazer upload: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao processar upload do documento", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Atualiza um documento existente
     * 
     * @param id ID do documento
     * @param arquivo Arquivo PDF (opcional)
     * @param titulo Título do documento
     * @param ordem Ordem de exibição
     * @param tipo Tipo do documento
     * @param qtdPontos Quantidade de pontos
     * @return ResponseEntity com o DTO do documento atualizado
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentoDto> atualizarDocumento(
            @PathVariable Integer id,
            @RequestParam(value = "arquivo", required = false) MultipartFile arquivo,
            @RequestParam("titulo") String titulo,
            @RequestParam(value = "ordem", required = false) Integer ordem,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "qtdPontos", required = false) Integer qtdPontos) {
        
        try {
            logger.info("Recebida solicitação para atualizar documento ID: " + id);
            DocumentoDto documentoAtualizado = documentoService.atualizarDocumento(id, titulo, arquivo, ordem, tipo, qtdPontos);
            return new ResponseEntity<>(documentoAtualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.warning("Erro de validação ao atualizar documento: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao processar atualização do documento", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Retorna todos os documentos
     * 
     * @return Lista de DocumentoDto
     */
    @GetMapping
    public ResponseEntity<List<DocumentoDto>> listarDocumentos() {
        logger.info("Listando todos os documentos");
        List<DocumentoDto> documentos = documentoService.listarTodosDocumentos();
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }
    
    /**
     * Busca um documento pelo ID
     * 
     * @param id ID do documento
     * @return DocumentoDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDto> buscarPorId(@PathVariable Integer id) {
        try {
            logger.info("Buscando documento ID: " + id);
            DocumentoDto documento = documentoService.buscarPorId(id);
            return new ResponseEntity<>(documento, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.warning("Documento não encontrado ID: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Exclui um documento pelo ID
     * 
     * @param id ID do documento
     * @return ResponseEntity indicando sucesso ou fracasso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDocumento(@PathVariable Integer id) {
        logger.info("Excluindo documento ID: " + id);
        boolean sucesso = documentoService.excluirDocumento(id);
        
        if (sucesso) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
