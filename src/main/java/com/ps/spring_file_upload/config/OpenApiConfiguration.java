package com.ps.spring_file_upload.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    private Info getFrontendApiInfo() {
        Contact contact = new Contact();
        contact.setName("Sinh PorSou");
        contact.setEmail("sinhporsurii@gmail.com");
        contact.setUrl("https://github.com/PorSou");

        return new Info()
                .title("File Upload API")
                .description("Frontend file upload API")
                .contact(contact)
                .version("1.0.1");
    }

    private Info getBackendApiInfo() {
        Contact contact = new Contact();
        contact.setName("Sinh PorSou");
        contact.setEmail("sinhporsurii@gmail.com");
        contact.setUrl("https://github.com/PorSou");

        return new Info()
                .title("File upload API")
                .description("Backend file upload API")
                .contact(contact)
                .version("1.0.1");
    }

    @Bean
    public GroupedOpenApi backendGroup() {
        return GroupedOpenApi.builder()
                .group("backend-api")
                .packagesToScan("com.ps.spring_file_upload.controller.backend")
                .addOpenApiCustomizer(openApi -> openApi.info(getBackendApiInfo()))
                .build();
    }


    @Bean
    public GroupedOpenApi frontendGroup() {
        return GroupedOpenApi.builder()
                .group("frontend-api")
                .packagesToScan("com.ps.spring_file_upload.controller.frontend")
                .addOpenApiCustomizer(openApi -> openApi.info(getFrontendApiInfo()))
                .build();
    }
}
