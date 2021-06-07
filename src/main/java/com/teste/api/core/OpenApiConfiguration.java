package com.teste.api.core;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 */
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Texo Test API")
                        .description("API Texo Test.")
                        .contact(new Contact().email("marceloddcatro@gmail.com").name("Marcelo Castro").url("https://www.linkedin.com/in/marceloddcastro/"))
                        .version("1.0.0")
                );
    }
}
