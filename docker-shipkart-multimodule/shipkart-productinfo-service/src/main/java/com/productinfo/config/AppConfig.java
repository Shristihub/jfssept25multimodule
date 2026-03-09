package com.productinfo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
	
//	Spring Cloud auto-configures Eureka to use RestClient for its HTTP communication.
//	When it finds a RestClient.Builder bean in the context, it uses it — 
//	but if that bean is @LoadBalanced, every HTTP call 
//	including http://localhost:8761/eureka gets routed through the load balancer,
//	which looks up localhost as a service name in Eureka — which doesn't exist.
	
//	Your service sees @Qualifier("loadBalancedBuilder")
//	uses the load balanced one for service-to-service call

	@Bean
	@LoadBalanced
	RestClient.Builder loadbalancedBuilder() {
		return RestClient.builder();
	}

	@Bean
	@Primary
	RestClient.Builder builder() {
		return RestClient.builder();
	}

}
