package com.cursos.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerConfigurationMethod() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/cursos/*"))
                .apis(RequestHandlerSelectors.basePackage("com.cursos"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Cursos API",
                "API para backend da aplicação Cursos",
                "1.0",
                "",
                new springfox.documentation.service.Contact("Henrique Buzachero", "https://github.com/Buzachero/", ""),
                "",
                "",
                Collections.emptyList()
        );
    }
}
