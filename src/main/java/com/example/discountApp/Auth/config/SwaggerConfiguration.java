package com.example.discountApp.Auth.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.PathSelectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
    }
    
}
