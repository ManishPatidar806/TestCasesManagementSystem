package com.testcase.test_case_management_system.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class OpenApiConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(getInfo());
    }

    Info getInfo() {
        return new Info().title("TestCase Management System").version("1.0.1").
                description("It is sample test case management System")
                .contact(new Contact().name("TestCase").url("url of Testcase website"))
                .license(new License().name("MIT-LICENSE").url("URL of LICENSE"))
                .termsOfService("URL of Terms&Service");
    }


}
