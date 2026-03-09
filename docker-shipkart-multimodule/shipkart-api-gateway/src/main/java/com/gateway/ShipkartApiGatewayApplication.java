package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class ShipkartApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipkartApiGatewayApplication.class, args);
	}
	   @Bean
	    CorsFilter corsFilter() {

	        CorsConfiguration config = new CorsConfiguration();

	        config.addAllowedOrigin("http://localhost:5173"); // React app
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        config.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source =
	                new UrlBasedCorsConfigurationSource();

	        source.registerCorsConfiguration("/**", config);

	        return new CorsFilter(source);
	    }

}
