package com.algaworks.socialbooks.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.name:notificationpromptmiddle}")
    private String applicationId;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.contact.name}")
    private String contactName;

    @Value("${swagger.contact.url}")
    private String contactURL;

    @Value("${swagger.contact.email}")
    private String contactEmail;

    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket apiDocs() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName(applicationId)
            .apiInfo(apiInfo())
            .select()
            .paths(paths())
            .build()
            .ignoredParameterTypes(ApiIgnore.class);
    }

    private Predicate<String> paths() {
        return or(
            regex("/api.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).description(description)
            .contact(contactName)
            .version(version).build();
    }
}