package com.example.propertiesinformationcollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class PropertiesServices {

	/**
	 * Список всех property
	 */
	private List<Property> properties;
}
