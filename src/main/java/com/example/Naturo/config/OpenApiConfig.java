package com.example.Naturo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                    title = "Naturo API",
                    version = "1.0",
                    description = "API REST de Naturo"
        ),
        servers = {
                @Server(
                    url = "http://localhost:8080",
                    description = "Local Server"
                )
        }
)

public class OpenApiConfig {
}
