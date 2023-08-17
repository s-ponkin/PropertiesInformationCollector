package com.example.propertiesinformationcollector.controller;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.model.Properties;
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
	public ResponseEntity<?> createTask(@RequestBody Services services) {
		Task task = taskStorage.create(services);
		return new ResponseEntity<>(task.getUuid(), HttpStatus.OK);
	}

	@GetMapping(value = "status")
	public ResponseEntity<StatusTask> readStatus(@RequestParam("uuid") UUID uuid) {
		Task task = taskStorage.read(uuid);
		return new ResponseEntity<>(task.getStatusTask(), HttpStatus.OK);
	}

	@GetMapping(value = "result")
	public ResponseEntity<Properties> readResult(@RequestParam("uuid") UUID uuid) {
		Task task = taskStorage.read(uuid);
		return task.getStatusTask() == StatusTask.SUCCESS
			? new ResponseEntity<>(task.getProperties(), HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
