package com.ispc.destinosapp.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${apiTitle}") String apiTitle,
            @Value("${apiDescription}") String apiDescription,
            @Value("${apiVersion}") String apiVersion,
            @Value("${apiContactName}") String apiContactName,
            @Value("${apiContactEmail}") String apiContactEmail,
            @Value("${apiContactUrl}") String apiContactUrl

    ) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion)
                        .contact(new Contact().name(apiContactName).email(apiContactEmail).url(apiContactUrl))

                );
    }
}