package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.exception.handler.PropertiesNotFoundException;
import com.example.propertiesinformationcollector.model.Properties;
import com.example.propertiesinformationcollector.model.Services;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.TaskStorage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@EnableScheduling
public class TaskStorageImpl implements TaskStorage {

	public static final Map<UUID, Task> taskMap = new HashMap<>();

	@Override
	public Task create(Services services) {
		UUID uuid = UUID.randomUUID();
		Task task = Task.builder()
			.uuid(uuid)
			.statusTask(StatusTask.IN_PROGRESS)
			.services(services)
			.build();
		taskMap.put(uuid, task);
		return task;
	}

	@Override
	public Task read(UUID uuid) {
		if(!taskMap.containsKey(uuid)){
			throw PropertiesNotFoundException.byUuid(uuid);
		}
		return taskMap.get(uuid);
	}

	@Override
	public Task update(UUID uuid, Properties properties) {
		if(!taskMap.containsKey(uuid)){
			throw PropertiesNotFoundException.byUuid(uuid);
		}
		Task task = Task.builder()
				.uuid(uuid)
				.statusTask(StatusTask.SUCCESS)
				.properties(properties)
				.build();
		taskMap.put(uuid, task);
		return task;
	}

	@Override
	public void delete(UUID uuid) {
		taskMap.remove(uuid);
	}

	@Override
	public synchronized Task getObjectInProgress(){
		for (Task task : taskMap.values()) {
			if(task.getStatusTask() == StatusTask.IN_PROGRESS){
				return task;
			}
		}
		return null;
	}
}
