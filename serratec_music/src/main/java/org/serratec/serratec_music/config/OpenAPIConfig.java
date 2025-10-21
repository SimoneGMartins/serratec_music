package org.serratec.serratec_music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration

public class OpenAPIConfig {
	 @Bean
	    public OpenAPI apiInfo() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("Serratec Music API")
	                        .description("API REST para gerenciamento de artistas e playlists")
	                        .version("v1"));
	    }
	}

