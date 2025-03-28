package com.THS_GROUP;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Autoriser toutes les origines spécifiées
        registry.addMapping("/**") // Appliquer CORS à toutes les routes
                .allowedOrigins("http://localhost:5173", "http://localhost:3000") // Origines autorisées
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Méthodes HTTP autorisées
                .allowedHeaders("*") // Tous les en-têtes sont autorisés
                .allowCredentials(true) // Autoriser les credentials (cookies, authentification, etc.)
                .maxAge(3600); // Durée de validité des pré-vols (en secondes)
    }

    // Vous pouvez ajouter d'autres configurations ici si nécessaire
}