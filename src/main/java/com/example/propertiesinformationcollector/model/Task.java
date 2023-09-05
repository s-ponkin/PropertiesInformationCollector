package com.example.propertiesinformationcollector.model;

import com.example.propertiesinformationcollector.enums.StatusTask;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@Builder
public class Task {

	/**
	 * Идентификатор задачи uuid
	 */
	private UUID uuid;

	/**
	 * Статус задачи
	 */
	private StatusTask statusTask;

	/**
	 * Информация о сервисах задачи
	 */
	private InfoAboutPropertiesInServices services;

	/**
	 * Результат работы программы
	 */
	private List<List<PropertyInfo>> propertiesServicesList;

	/**
	 * Дата создания задачи
	 */
	private Date dateOfCreation;
}
