package com.listo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Listo API")
                        .version("v1")
                        .description("Listo - Your practical tool for managing daily tasks.")
                        .termsOfService("https://github.com/gustavoglins/listo/blob/main/LICENSE.md")
                        .license(
                                new License()
                                        .name("Listo 1.0")
                                        .url("https://github.com/gustavoglins/listo")
                        )
                );
    }
}
