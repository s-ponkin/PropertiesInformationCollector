package com.example.propertiesinformationcollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Async;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Service {

	/**
	 * Имя сервиса
	 */
	private String name;

	/**
	 * Путь до файла с property сервиса
	 */
	private String path;
}
