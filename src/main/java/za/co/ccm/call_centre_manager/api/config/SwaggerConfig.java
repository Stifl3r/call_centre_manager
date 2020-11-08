package za.co.ccm.call_centre_manager.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("za.co.ccm.call_centre_manager.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalTime.class, String.class)
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .apiInfo(metaData());
    }


    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Call Centre Manager REST API")
                .description("CCM api managing teams and agents")
                .version("1.0")
                .contact(new Contact(
                        "Arnold Madisa Dev House",
                        "https://www.linkedin.com/in/arnold-madisa-b9064446/",
                        "atmadisa1@gmail.com"))
                .build();
    }
}
