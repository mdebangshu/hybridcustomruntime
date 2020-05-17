package com.lightweight.hybridcustomruntime.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

	@Bean
	@Lazy
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}