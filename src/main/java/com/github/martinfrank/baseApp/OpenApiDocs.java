package com.github.martinfrank.baseApp;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Links;

@Configuration
public class OpenApiDocs {

    @Bean
    public OpenAPI meteoriteOpenAPI() {
        SpringDocUtils.getConfig().addResponseTypeToIgnore(Links.class);
        return new OpenAPI()
                .info(new Info().title("Meteorite API")
                        .description("Meteorite API documentation. This API intended to be used by Meteorite staff mobile apps and Meteorite customers.")
                        .version("v1.0")
                );
    }

}
