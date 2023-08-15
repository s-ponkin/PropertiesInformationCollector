package com.example.propertiesinformationcollector.controller;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.model.Properties;
import com.example.propertiesinformationcollector.model.Services;
import com.example.propertiesinformationcollector.service.TaskStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class PropertiesController {

	private final TaskStorage taskStorage;

	@PostMapping(value = "/task/add")
	public ResponseEntity<?> createTask(@RequestBody Services services) {
		UUID uuid = taskStorage.create(services);
		return new ResponseEntity<>(uuid, HttpStatus.OK);
	}

	@GetMapping(value = "/task/status")
	public ResponseEntity<StatusTask> readStatus(@RequestParam("uuid") UUID uuid) {
		StatusTask statusTask = taskStorage.getStatus(uuid);
		return new ResponseEntity<>(statusTask, HttpStatus.OK);
	}

	@GetMapping(value = "/task/result")
	public ResponseEntity<Properties> readResult(@RequestParam("uuid") UUID uuid) {
		Properties properties = taskStorage.getResult(uuid);
		return properties != null
			? new ResponseEntity<>(properties, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
