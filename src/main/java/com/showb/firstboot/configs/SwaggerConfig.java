package com.showb.firstboot.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final Environment environment;

    @Bean
    public OpenAPI openAPI() {
        String profile = environment.getProperty("spring.profiles.active");
        String application = Objects.requireNonNullElse(environment.getProperty("spring.application.name"), "")
                .toLowerCase();

        List<Server> servers = new ArrayList<>();
        if ("prod".equals(profile)) {
            servers.add(new Server().url(String.format("https://%s.rms.kurly.services", application)));
        } else if ("stg".equals(profile)) {
            servers.add(new Server().url(String.format("https://%s.rms.stg.kurly.services", application)));
        } else if ("dev".equals(profile)) {
            servers.add(new Server().url(String.format("https://%s.rms.dev.kurly.services", application)));
        }

        String securitySchemeName = "Bearer";
        return new OpenAPI()
                .servers(servers)
                .components(
                        new Components().addSecuritySchemes(
                                securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme(securitySchemeName)
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")
                        ))
                .info(apiInfo())
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }

    private Info apiInfo() {
        String applicationName = environment.getProperty("spring.application.name");
        String profile = environment.getProperty("spring.profiles.active");
        if (!"prod".equals(profile)){
            applicationName = "(" + profile + ")" + " " + applicationName;
        }

        return new Info()
                .title(applicationName)
                .description("ShowB FirstBoot REST API")
                .version("1.0.0");
    }

}
