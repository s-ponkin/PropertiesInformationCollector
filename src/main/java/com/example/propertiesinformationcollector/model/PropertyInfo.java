package com.example.propertiesinformationcollector.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyInfo {

	/**
	 * Имя проперти
	 */
	String name;

	/**
	 * Значение проперти
	 */
	String value;

	/**
	 * Описание проперти
	 */
	String description;
}
