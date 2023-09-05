package com.example.propertiesinformationcollector.exception.handler.exceptions;

import java.util.UUID;

public class TaskNotBeenCompletedException extends RuntimeException{
	public TaskNotBeenCompletedException(String message) {
		super(message);
	}

	public static TaskNotFoundException getMes() {
		return new TaskNotFoundException("The task was not completed to the end.");
	}
}
