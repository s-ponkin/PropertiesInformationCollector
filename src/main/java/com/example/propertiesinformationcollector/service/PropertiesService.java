package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.Properties;
import com.example.propertiesinformationcollector.model.Services;

import java.util.concurrent.CompletableFuture;

public interface PropertiesService {

	/**
	 * Создает задачу для получение списка настроек и вернуть идентификатор задачи
	 *
	 * @param services сервисы с информацией о настройках
	 *                 * @return результат выполнения задачи
	 */
	CompletableFuture<Properties> create(Services services);
}
