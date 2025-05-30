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
            
            String warnMsg = "Credenciais do Cloudinary não configuradas. Usando configuração para desenvolvimento.";
            logger.warning(warnMsg);
            
            // Configuração para desenvolvimento/teste com valores fictícios
            Map<String, String> mockConfig = new HashMap<>();
            mockConfig.put("cloud_name", "dev-cloud");
            mockConfig.put("api_key", "dev-key");
            mockConfig.put("api_secret", "dev-secret");
            
            logger.info("Cloudinary configurado no modo de desenvolvimento");
            return new Cloudinary(mockConfig);
        }
        
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        
        logger.info("Cloudinary configurado com sucesso para o cloud_name: " + cloudName);
        return new Cloudinary(config);
    }
}
