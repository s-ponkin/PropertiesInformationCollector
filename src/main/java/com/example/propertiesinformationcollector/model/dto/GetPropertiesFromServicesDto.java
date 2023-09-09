package com.example.propertiesinformationcollector.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class GetPropertiesFromServicesDto {

	/**
	 * Список всех property
	 */
	List<Property> properties;

	@Data
	@Builder
	public static class Property {

		/**
		 * Имя property
		 */
		String name;

		/**
		 * Список объектов с информацией о property
		 */
		List<ServicesValue> servicesValue;

		/**
		 * Результат сравнения значений всех сервисов в property
		 */
		boolean allValuesEqual;
		@Data
		@Builder
		public static class ServicesValue {

			/**
			 * Описание property
			 */
			@JsonInclude(JsonInclude.Include.NON_NULL)
			String description;

			/**
			 * Имя сервиса
			 */
			String service;

			/**
			 * Значение property
			 */
			String value;
		}
	}
}
