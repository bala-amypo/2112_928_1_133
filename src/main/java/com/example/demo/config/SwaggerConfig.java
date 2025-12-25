package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Multi-Location Inventory Balancer API")
                        .version("1.0")
                        .description("Secured backend for inventory balancing")
                )
                // ✅ PORT KEPT (as requested)
                .servers(List.of(
                        new Server()
                                .url("https://9129.408procr.amypo.ai")
                                .description("Hosted Server")
                ))
                .addSecurityItem(
                        new SecurityRequirement().addList(securitySchemeName)
                )
                .components(
                        new io.swagger.v3.oas.models.Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
