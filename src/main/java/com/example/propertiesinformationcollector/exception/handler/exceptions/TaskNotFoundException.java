package com.example.propertiesinformationcollector.exception.handler.exceptions;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException(String message) {
		super(message);
	}

	public static TaskNotFoundException byUuid(UUID uuid) {
		return new TaskNotFoundException(String.format("Not found task by uuid: %s", uuid));
	}
}
