package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.exception.handler.TaskNotFoundException;
import com.example.propertiesinformationcollector.model.PropertiesServices;
import com.example.propertiesinformationcollector.model.Services;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.TaskStorage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskStorageImpl implements TaskStorage {

	public static final Map<UUID, Task> taskMap = new HashMap<>();

	@Override
	public Task create(Services services) {
		UUID uuid = UUID.randomUUID();
		Task task = Task.builder()
			.uuid(uuid)
			.statusTask(StatusTask.IN_PROGRESS)
			.services(services)
			.dateOfCreation(new Date())
			.build();
		taskMap.put(uuid, task);
		return task;
	}

	@Override
	public Task getByUuid(UUID uuid) {
		if(!taskMap.containsKey(uuid)){
			throw TaskNotFoundException.byUuid(uuid);
		}
		return taskMap.get(uuid);
	}

	@Override
	public void updateByUuid(UUID uuid, PropertiesServices properties) {
		if(!taskMap.containsKey(uuid)){
			throw TaskNotFoundException.byUuid(uuid);
		}
		Task task = Task.builder()
				.uuid(uuid)
				.statusTask(StatusTask.SUCCESS)
				.propertiesServices(properties)
				.build();
		taskMap.put(uuid, task);
	}

	@Override
	public synchronized Task findFirstTaskInProgress(){
        return taskMap
			.values()
			.stream()
			.filter(element -> element.getStatusTask() == StatusTask.IN_PROGRESS)
			.min(Comparator.comparing(Task::getDateOfCreation))
			.orElse(null);
	}
}
