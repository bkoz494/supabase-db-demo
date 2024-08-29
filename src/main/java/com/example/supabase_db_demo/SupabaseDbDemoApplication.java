package com.example.supabase_db_demo;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@SpringBootApplication
public class SupabaseDbDemoApplication {

	@Value("${jwt.secret}")
	private String secretKeyString;


	public static void main(String[] args) {
		SpringApplication.run(SupabaseDbDemoApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Bean
	public JwtDecoder jwtDecoder(){

//		String secretKeyString = "vSixGKD5FNmrrnCLMwVE4s6eSc8lG2mz/Rjz2jL4BsAUfMFkiToiyMT9XRp9cW/k2XL8/zJSckDpF2KeFoXjVA==";
		byte[] secretKeyBytes = secretKeyString.getBytes();
		SecretKey secretKey = new SecretKeySpec(secretKeyBytes, 0, secretKeyBytes.length, "HmacSHA256");

		return NimbusJwtDecoder.withSecretKey(secretKey).build();
	}

}
