package com.lightweight.hybridcustomruntime.service;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lightweight.hybridcustomruntime.Application;
import com.lightweight.hybridcustomruntime.model.WorldTime;

@Service
public class WorldTimeService {

	public WorldTime getWorldTime(String country) {
		GenericApplicationContext context = Application.getInstance().getContext();
		RestTemplate restTemplate = context.getBean(RestTemplate.class);
		Environment env = context.getEnvironment();
		WorldTime worldTime = restTemplate.getForObject(env.getProperty("aws.weather.gateway.url"), WorldTime.class,
				country);
		return worldTime;
	}
}