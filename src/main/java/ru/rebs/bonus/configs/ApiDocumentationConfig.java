package ru.rebs.bonus.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {

        @Bean
        public OpenAPI apiDocConfig() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Сервис Rebs Bonus")
                            .description("Сервис позволяет проводить операции по получению, добавлению и списанию бонусов по субъекту")
                            .version("0.0.1")
                            .contact(new Contact()
                                    .name("Rebs Bonus")
                                    .email("support-rebs-bonus@aa.surnachev.com")))
                    .externalDocs(new ExternalDocumentation()
                            .description("Demo apps")
                            .url("http://79.137.197.181:8080/"));
        }
}
