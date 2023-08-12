package com.example.propertiesinformationcollector.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Services {

	/**
	 * Список сервисов
	 */
	private List<Service> services;

	/**
	 * Список игнорируемых property
	 */
	private List<String> ignorePropertiesList;
}
