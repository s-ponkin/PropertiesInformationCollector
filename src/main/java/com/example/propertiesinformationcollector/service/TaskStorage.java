package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.PropertiesServices;
import com.example.propertiesinformationcollector.model.Services;
import com.example.propertiesinformationcollector.model.Task;

import java.util.UUID;

public interface TaskStorage {

	/**
	 * Создает задачу для выполнения
	 *
	 * @param services сервисы с информацией о настройках
	 *
	 * @return идентификатор задачи
	 */
	Task create(Services services);

	/**
	 * Получить задачу по uuid
	 *
	 * @param uuid uuid задачи
	 *
	 * @return Задача
	 */
	Task getByUuid(UUID uuid);

	/**
	 * Обновить Properties задачи по uuid
	 *
	 * @param uuid uuid задачи
	 */
	void updateByUuid(UUID uuid, PropertiesServices propertiesServices);

	/**
	 * Найти первую задачу в очереди, которую нужно выполнить
	 *
	 * @return первую задачу в очереди
	 */
	Task findFirstTaskInProgress();
}
