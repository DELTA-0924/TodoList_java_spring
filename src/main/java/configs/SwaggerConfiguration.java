package configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI Config(){
        return new OpenAPI().info(new Info().title("Task Manager")
                .version("1.0")
                .description("Api documentation for Task Manager application "));
    }
}
