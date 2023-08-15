package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.model.Properties;
import com.example.propertiesinformationcollector.model.Services;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.TaskStorage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TaskStorageImpl implements TaskStorage {

	public static final Map<UUID, Task> taskMap = new HashMap<>();
	private static final Logger logger = Logger.getLogger(TaskStorageImpl.class.getName());

	@Override
	public UUID create(Services services) {
		UUID uuid = UUID.randomUUID();
		Task task = Task.builder()
			.uuid(uuid)
			.statusTask(StatusTask.IN_PROGRESS)
			.services(services)
			.build();
		taskMap.put(uuid, task);
		return uuid;
	}

	@Override
	public StatusTask getStatus(UUID uuid) {
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
