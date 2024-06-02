package springbootlearning.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Title: Spring Boot Learning API")
                        .description("Description: Spring Boot Learning API documentation")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("Luan Braz")
                                .url("http://luanbraz.com")
                                .email("luan_silvacpv@hotmail.com"))
                        .license(new License().name("License 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot Learning Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("public-api")
//                .pathsToMatch("/public/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//                .group("admin-api")
//                .pathsToMatch("/admin/**")
//                .build();
//    }
}
