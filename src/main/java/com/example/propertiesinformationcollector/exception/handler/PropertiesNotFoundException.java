package com.example.propertiesinformationcollector.exception.handler;

import java.util.UUID;

public class PropertiesNotFoundException extends RuntimeException {

	public PropertiesNotFoundException(String message) {
		super(message);
	}

	public static PropertiesNotFoundException byUuid(UUID uuid) {
		return new PropertiesNotFoundException(String.format("Not found by uuid: %s", uuid));
	}
}
