package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.Properties;
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
	Task read(UUID uuid);

	/**
	 * Обновить Properties задачи по uuid
	 *
	 * @param uuid uuid задачи
	 *
	 * @return Обновлённая задача
	 */
	Task update(UUID uuid, Properties properties);

	/**
	 * Удалить задачу по uuid
	 *
	 * @param uuid uuid задачи
	 */
	void delete(UUID uuid);

	/**
	 * Получить задачу, которую нужно выполнить
	 */
	Task getObjectInProgress();
}
