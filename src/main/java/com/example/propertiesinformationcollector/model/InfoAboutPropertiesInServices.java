package com.example.propertiesinformationcollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@AllArgsConstructor
@Value
@Builder
public class InfoAboutPropertiesInServices {
	/**
	 * Список сервисов
	 */
	List<ServiceInfo> services;

	/**
	 * Список игнорируемых property
	 */
	List<String> ignorePropertiesList;

}
