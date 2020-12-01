package com.stackroute.favouriteservice.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
       return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select().paths(postPath()).build();
    }
    
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Favourite API")
				.description("FavouriteService for developers")
				.termsOfServiceUrl("http://stackroute.in")
				.contact("agupta35@stackroute.in").license("Stackroute License")
				.licenseUrl("agupta35@gmail.com").version("1.0").build();
	}

    
    private Predicate<String> postPath() {
    	return regex("/api/");
    }
}
