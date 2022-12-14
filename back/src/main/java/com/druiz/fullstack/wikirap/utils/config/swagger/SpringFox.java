package com.druiz.fullstack.wikirap.utils.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFox {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("WIKIRAP API")
                .description("API reference for developers")
                .termsOfServiceUrl("http://google.com")
                .contact(new Contact(
                        "Daniel",
                        "http://google.com",
                        "daniel@example.com")).license("JavaInUse License")
                .licenseUrl("javainuse@gmail.com").version("1.0").build();
    }
}
