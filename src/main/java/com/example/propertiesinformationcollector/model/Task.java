package com.example.propertiesinformationcollector.model;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
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
	private Services services;

	/**
	 * Результат работы программы
	 */
	private PropertiesServices propertiesServices;

	/**
	 * Дата создания задачи
	 */
	private Date dateOfCreation;
}
