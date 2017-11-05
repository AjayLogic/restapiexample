package com.dvtrsc.restexample.ecosystem;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.annotations.Info;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/*
 * SwaggerConfig. Swagger online documentation configuration 
 * D.Tordera, 20171031
 * 
 */

@Configuration
@EnableSwagger2

public class SwaggerConfig {
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .host("vps355126.ovh.net")
          .pathMapping("/restexample")
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.dvtrsc.restexample.controllers"))             
          .paths(PathSelectors.any())                          
          .build()          
          .apiInfo(getApiInfo());                                          
    }        
    
    @SuppressWarnings("rawtypes")
	private ApiInfo getApiInfo()
    {
    	return new ApiInfo(
    			"COM.DVTRSC.RESTAPIEXAMPLE",	
				"SpringBoot REST/API Example",
				"v0.1",
				"",
				new Contact("D.Tordera","https://davidscodevault.com","dtordera@gmail.com"),
				"","",
				new ArrayList<VendorExtension>());				
    }
}