//package com.jota_nunes_back_end.jotanunes.services;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.jota_nunes_back_end.jotanunes.dtos.DocumentoDto;
//import com.jota_nunes_back_end.jotanunes.models.Documento;
//import com.jota_nunes_back_end.jotanunes.repositories.DocumentoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
///**
// * Serviço responsável pelo gerenciamento de documentos PDF com upload para Cloudinary
// */
//@Service
//public class DocumentoService {
//
//    private static final Logger logger = Logger.getLogger(DocumentoService.class.getName());
//
//    @Autowired
//    private DocumentoRepository documentoRepository;
//
//    @Autowired
//    private Cloudinary cloudinary;
//
//    /**
//     * Salva um novo documento PDF no Cloudinary e seus metadados no banco de dados
//     *
//     * @param titulo Título do documento
//     * @param arquivo Arquivo PDF
//     * @param ordem Ordem de exibição do documento
//     * @param tipo Tipo do documento
//     * @param qtdPontos Quantidade de pontos associados ao documento
//     * @return DTO com os dados do documento salvo
//     * @throws IOException Se ocorrer erro no upload
//     */
//    public DocumentoDto salvarDocumento(String titulo, MultipartFile arquivo,
//            Integer ordem, String tipo, Integer qtdPontos) throws IOException {
//
//        if (arquivo == null || arquivo.isEmpty()) {
//            throw new IllegalArgumentException("Arquivo PDF não pode ser vazio");
//        }
//
//        // Verifica o tipo do arquivo
//        String contentType = arquivo.getContentType();
//        if (contentType == null || !contentType.equals("application/pdf")) {
//            throw new IllegalArgumentException("O arquivo deve ser um PDF válido");
//        }
//
//        logger.info("Iniciando upload do documento '" + titulo + "' para o Cloudinary");
//
//        try {
//            // Upload para o Cloudinary
//            Map<?, ?> uploadResult = cloudinary.uploader().upload(
//                arquivo.getBytes(),
//                ObjectUtils.asMap(
//                    "resource_type", "auto",
//                    "folder", "documentos"
//                )
//            );
//
//            // Obter a URL pública do documento
//            String url = uploadResult.get("secure_url").toString();
//
//            // Salvar os metadados no banco de dados
//            Documento documento = new Documento();
//            documento.setTitulo(titulo);
//            documento.setUrl(url);
//            documento.setOrdem(ordem);
//            documento.setTipo(tipo);
//            documento.setQtdPontos(qtdPontos);
//
//            documento = documentoRepository.save(documento);
//
//            logger.info("Documento '" + titulo + "' salvo com sucesso. ID: " + documento.getId());
//
//            // Converter para DTO e retornar
//            return convertToDto(documento);
//
//        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Erro ao fazer upload do documento para o Cloudinary", e);
//            throw e;
//        }
//    }
//
//    /**
//     * Atualiza um documento PDF existente
//     *
//     * @param id ID do documento a ser atualizado
//     * @param titulo Novo título do documento
//     * @param arquivo Novo arquivo PDF (opcional)
//     * @param ordem Nova ordem de exibição
//     * @param tipo Novo tipo do documento
//     * @param qtdPontos Nova quantidade de pontos
//     * @return DTO com os dados atualizados
//     * @throws IOException Se ocorrer erro no upload
//     */
//    public DocumentoDto atualizarDocumento(Integer id, String titulo, MultipartFile arquivo,
//            Integer ordem, String tipo, Integer qtdPontos) throws IOException {
//
//        // Buscar o documento no banco de dados
//        Optional<Documento> docOptional = documentoRepository.findById(id);
//        if (docOptional.isEmpty()) {
//            throw new IllegalArgumentException("Documento não encontrado com o ID: " + id);
//        }
//
//        Documento documento = docOptional.get();
//        String url = documento.getUrl(); // URL atual
//
//        // Se um novo arquivo foi fornecido, fazer upload e atualizar a URL
//        if (arquivo != null && !arquivo.isEmpty()) {
//            // Verifica o tipo do arquivo
//            String contentType = arquivo.getContentType();
//            if (contentType == null || !contentType.equals("application/pdf")) {
//                throw new IllegalArgumentException("O arquivo deve ser um PDF válido");
//            }
//
//            // Upload para o Cloudinary
//            Map<?, ?> uploadResult = cloudinary.uploader().upload(
//                arquivo.getBytes(),
//                ObjectUtils.asMap(
//                    "resource_type", "auto",
//                    "folder", "documentos"
//                )
//            );
//
//            // Atualizar a URL
//            url = uploadResult.get("secure_url").toString();
//        }
//
//        // Atualizar os metadados
//        documento.setTitulo(titulo);
//        documento.setUrl(url);
//        documento.setOrdem(ordem);
//        documento.setTipo(tipo);
//        documento.setQtdPontos(qtdPontos);
//
//        documento = documentoRepository.save(documento);
//
//        logger.info("Documento atualizado com sucesso. ID: " + documento.getId());
//
//        // Converter para DTO e retornar
//        return convertToDto(documento);
//    }
//
//    /**
//     * Retorna todos os documentos cadastrados
//     *
//     * @return Lista de documentos
//     */
//    public List<DocumentoDto> listarTodosDocumentos() {
//        List<Documento> documentos = documentoRepository.findAll();
//        return documentos.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Busca um documento pelo ID
//     *
//     * @param id ID do documento
//     * @return DTO com os dados do documento
//     */
//    public DocumentoDto buscarPorId(Integer id) {
//        Optional<Documento> docOptional = documentoRepository.findById(id);
//        if (docOptional.isEmpty()) {
//            throw new IllegalArgumentException("Documento não encontrado com o ID: " + id);
//        }
//
//        return convertToDto(docOptional.get());
//    }
//
//    /**
//     * Exclui um documento pelo ID
//     *
//     * @param id ID do documento a ser excluído
//     * @return true se o documento foi excluído com sucesso
//     */
//    public boolean excluirDocumento(Integer id) {
//        Optional<Documento> docOptional = documentoRepository.findById(id);
//        if (docOptional.isEmpty()) {
//            return false;
//        }
//
//        documentoRepository.deleteById(id);
//        logger.info("Documento excluído com sucesso. ID: " + id);
//        return true;
//    }
//
//    /**
//     * Converte um objeto Documento para DocumentoDto
//     *
//     * @param documento Objeto documento
//     * @return Objeto DocumentoDto
//     */
//    private DocumentoDto convertToDto(Documento documento) {
//        return DocumentoDto.builder()
//                .id(documento.getId())
//                .titulo(documento.getTitulo())
//                .url(documento.getUrl())
//                .ordem(documento.getOrdem())
//                .tipo(documento.getTipo())
//                .qtdPontos(documento.getQtdPontos())
//                .build();
//    }
//}
