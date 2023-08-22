package com.example.propertiesinformationcollector.controller;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.model.PropertiesServices;
import com.example.propertiesinformationcollector.model.Services;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.TaskStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("task")
public class TaskController {

	private final TaskStorage taskStorage;

	@PostMapping
	public UUID createTask(@RequestBody Services services) {
		Task task = taskStorage.create(services);
		return task.getUuid();
	}

	@GetMapping(value = "status")
	public StatusTask readStatus(@RequestParam("uuid") UUID uuid) {
		Task task = taskStorage.getByUuid(uuid);
		return task.getStatusTask();
	}

	@GetMapping(value = "result")
	@ResponseBody
	public PropertiesServices readResult(@RequestParam("uuid") UUID uuid) {
		Task task = taskStorage.getByUuid(uuid);
		if(task.getStatusTask() == StatusTask.IN_PROGRESS){
			new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return task.getPropertiesServices();
	}
}
