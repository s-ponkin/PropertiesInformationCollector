package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.model.Properties;
import com.example.propertiesinformationcollector.model.Services;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.property.PropertyUtil;
import com.example.propertiesinformationcollector.service.PropertiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PropertiesServiceImpl implements PropertiesService {

	private static final Map<UUID, Task> taskMap = new HashMap<>();
	private static final Logger logger = Logger.getLogger(PropertiesServiceImpl.class.getName());

	@Override
	public UUID createTask(Services services) {
		UUID uuid = UUID.randomUUID();
		Task task = Task.builder()
			.uuid(uuid)
			.statusTask(StatusTask.IN_PROGRESS)
			.properties(startTask(services))
			.build();
		taskMap.put(uuid, task);
		return uuid;
	}

	@Async
	protected CompletableFuture<Properties> startTask(Services services) {
		Properties results = PropertyUtil.getProperties(services);
		return CompletableFuture.completedFuture(results);
	}

	@Override
	public StatusTask getStatus(UUID uuid) {
		if (taskMap.get(uuid).getProperties().isDone()) {
			taskMap.get(uuid).setStatusTask(StatusTask.SUCCESS);
		}
		return taskMap.get(uuid).getStatusTask();
	}

	@Override
	public Properties getResult(UUID uuid) {
		if (taskMap.get(uuid).getStatusTask() == StatusTask.IN_PROGRESS) {
			return null;
		}
		try {
			return taskMap.get(uuid).getProperties().get();
		} catch (InterruptedException | ExecutionException e) {
			logger.log(Level.WARNING, e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
