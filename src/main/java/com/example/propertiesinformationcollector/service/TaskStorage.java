package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskStorage {

	/**
	 * Создает задачу для выполнения
	 *
	 * @param services сервисы с информацией о настройках
	 *
	 * @return идентификатор задачи
	 */
	Task create(InfoAboutPropertiesInServices services);

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
	 * @param allListWithPropertyList Список списков с информацией о всех проперти
	 *
	 * @return обновлённую задачу
	 */
	Task updateByUuid(UUID uuid, List<List<PropertyInfo>> allListWithPropertyList);

	/**
	 * Найти первую задачу в очереди, которую нужно выполнить
	 *
	 * @return первую задачу в очереди
	 */
	Task findFirstTaskInProgress();
}
