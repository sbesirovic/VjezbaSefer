package com.example.FirstApp;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApiConfig()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Lists.newArrayList(securityScheme()))
                .securityContexts(Lists.newArrayList(securityContext()));
    }


    @Bean
    SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    List<SecurityReference> defaultAuth() {

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] =  new AuthorizationScope("global", "accessEverything");;

        return Lists.newArrayList(
                new SecurityReference("Bearer", authorizationScopes ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Milijunas REST API Document")
                .description("Swagger first example")
                .contact(new Contact("Sefer Beširović", "www.example.com", "seferbesirovic@hotmail.com"))
                .termsOfServiceUrl("localhost")
                .version("1.0")
                .build();
    }

    private ApiKey securityScheme() {
        return new ApiKey("Bearer", "Authorization", "header");
    }
}
