package com.example.supabase_db_demo;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

@SpringBootApplication
public class SupabaseDbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupabaseDbDemoApplication.class, args);
	}

	/*@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}*/

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

}
