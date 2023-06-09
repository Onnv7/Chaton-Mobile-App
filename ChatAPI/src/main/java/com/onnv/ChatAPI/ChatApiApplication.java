package com.onnv.ChatAPI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@SpringBootApplication
//@EnableSwagger2
//@OpenAPIDefinition
public class ChatApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApiApplication.class, args);
	}
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("api")
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.onnv.ChatAPI.controller"))
//				.paths(PathSelectors.any())
//				.build()
//				.apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("Chat API")
//				.description("Chat API description")
//				.license("nva6112002@gmail.com")
//				.version("1.0")
//				.build();
//	}
}
