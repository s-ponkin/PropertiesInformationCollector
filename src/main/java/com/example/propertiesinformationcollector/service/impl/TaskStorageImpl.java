package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.enums.StatusTask;
import com.example.propertiesinformationcollector.exception.handler.exceptions.TaskNotFoundException;
import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.TaskStorage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskStorageImpl implements TaskStorage {

	public static final Map<UUID, Task> taskMap = new HashMap<>();

	@Override
	public Task create(InfoAboutPropertiesInServices services) {
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
	public void updateByUuid(UUID uuid, List<List<PropertyInfo>> propertiesServicesList) {
		if(!taskMap.containsKey(uuid)){
			throw TaskNotFoundException.byUuid(uuid);
		}
		Task task = Task.builder()
				.uuid(uuid)
				.statusTask(StatusTask.SUCCESS)
				.propertiesServicesList(propertiesServicesList)
				.build();
		taskMap.put(uuid, task);
		System.out.println(taskMap);
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
