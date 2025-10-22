package com.meli.order_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Global configuration class for OpenAPI (Swagger) documentation.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates a custom OpenAPI bean to provide global API information.
     * This information will be displayed at the top of the Swagger UI page.
     *
     * @return A configured OpenAPI object.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MELI Order System API")
                        .version("1.0.0")
                        .description("RESTful API for managing orders in the MELI system.")
                );
    }
}