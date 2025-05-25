package com.jota_nunes_back_end.jotanunes.configs;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Configuração do Cloudinary para armazenamento de documentos na nuvem
 */
@Configuration
public class CloudinaryConfig {

    private static final Logger logger = Logger.getLogger(CloudinaryConfig.class.getName());

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    /**
     * Cria e configura o bean do Cloudinary
     * @return Instância configurada do Cloudinary
     */    @Bean
    public Cloudinary cloudinary() {
        // Verifica se as credenciais foram fornecidas
        if (cloudName == null || cloudName.trim().isEmpty() ||
            apiKey == null || apiKey.trim().isEmpty() ||
            apiSecret == null || apiSecret.trim().isEmpty()) {
            
            String errorMsg = "Credenciais do Cloudinary não configuradas. Defina as variáveis de ambiente " +
                             "CLOUDINARY_CLOUD_NAME, CLOUDINARY_API_KEY e CLOUDINARY_API_SECRET ou use o perfil de desenvolvimento.";
            logger.severe(errorMsg);
            throw new IllegalStateException(errorMsg);
        }
        
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        
        logger.info("Cloudinary configurado com sucesso para o cloud_name: " + cloudName);
        return new Cloudinary(config);
    }
}
