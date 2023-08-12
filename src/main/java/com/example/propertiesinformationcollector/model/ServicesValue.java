package com.example.propertiesinformationcollector.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ServicesValue {

	/**
	 * Описание property
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;

	/**
	 * Имя сервиса
	 */
	private String serviceName;

	/**
	 * Значение property
	 */
	private String value;
}
