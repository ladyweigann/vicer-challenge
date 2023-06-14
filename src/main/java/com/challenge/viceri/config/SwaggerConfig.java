package com.challenge.viceri.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi httpApi() {
        return GroupedOpenApi.builder()
                .group("http")
                .pathsToMatch("/**")
                .build();
    }
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(
                new Info().title("Viceri Code Challenge")
                        .version("1.0")
                        .description("Java and Spring Application to manage tasks")
                        .license(getLicense()));
    }

    private License getLicense() {
        License license = new License();
        license.setName("Apache License Version 2.0");
        return license;
    }
}
