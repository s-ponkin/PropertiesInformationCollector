package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.model.Properties;
import com.example.propertiesinformationcollector.model.Services;
import com.example.propertiesinformationcollector.property.PropertyUtil;
import com.example.propertiesinformationcollector.service.PropertiesService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PropertiesServiceImpl implements PropertiesService {

	@Async
	@Override
	public CompletableFuture<Properties> create(Services services) {
		Properties results = PropertyUtil.getProperties(services);
		return CompletableFuture.completedFuture(results);
	}
}
