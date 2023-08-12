package com.example.propertiesinformationcollector.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Property {

	/**
	 * Имя property
	 */
	private String name;

	/**
	 * Список объектов с информацией о property
	 */
	private List<ServicesValue> servicesValueList;

	/**
	 * Результат сравнения значений всех сервисов в property
	 */
	private boolean allValuesEqual;
}
