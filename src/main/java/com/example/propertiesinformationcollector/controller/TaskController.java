package com.example.propertiesinformationcollector.controller;

import com.example.propertiesinformationcollector.enums.StatusTask;
import com.example.propertiesinformationcollector.exception.handler.exceptions.TaskNotBeenCompletedException;
import com.example.propertiesinformationcollector.model.dto.CreateTaskCollectPropertiesFromServicesRequestDto;
import com.example.propertiesinformationcollector.model.dto.GetPropertiesFromServicesDto;
import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.TaskStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("task")
public class TaskController {

	private final TaskStorage taskStorage;
	private final ConversionService conversionService;

	@PostMapping
	public UUID createTask(@RequestBody CreateTaskCollectPropertiesFromServicesRequestDto services) {
		InfoAboutPropertiesInServices info = conversionService.convert(services, InfoAboutPropertiesInServices.class);
		Task task = taskStorage.create(info);
		return task.getUuid();
	}

	@GetMapping(value = "status")
	public StatusTask readStatus(@RequestParam("uuid") UUID uuid) {
		Task task = taskStorage.getByUuid(uuid);
		return task.getStatusTask();
	}

	@GetMapping(value = "result")
	@ResponseBody
	public GetPropertiesFromServicesDto readResult(@RequestParam("uuid") UUID uuid) {
		Task task = taskStorage.getByUuid(uuid);
		if(task.getStatusTask() == StatusTask.IN_PROGRESS){
			throw TaskNotBeenCompletedException.getMes();
		}
//		return task.getPropertiesServicesList();
		return null;
	}
}
