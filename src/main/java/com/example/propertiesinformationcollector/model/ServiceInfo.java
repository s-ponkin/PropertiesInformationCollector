package com.example.propertiesinformationcollector.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ServiceInfo {

	/**
	 * Имя сервиса
	 */
	String name;

	/**
	 * Путь до файла с проперти сервиса
	 */
	String pathToProperties;
}
