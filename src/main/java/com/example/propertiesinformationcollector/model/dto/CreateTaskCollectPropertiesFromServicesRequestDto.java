package com.example.propertiesinformationcollector.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Value
@Builder
public class CreateTaskCollectPropertiesFromServicesRequestDto {

	/**
	 * Список сервисов
	 */
	List<CreateTaskCollectPropertiesFromServicesRequestServicesDto> services;

	/**
	 * Список игнорируемых property
	 */
	List<String> ignorePropertiesList;

	@AllArgsConstructor
	@Value
	@Builder
	public static class CreateTaskCollectPropertiesFromServicesRequestServicesDto{

		/**
		 * Имя сервиса
		 */
		String name;

		/**
		 * Путь до файла с property сервиса
		 */
		String path;
	}
}
