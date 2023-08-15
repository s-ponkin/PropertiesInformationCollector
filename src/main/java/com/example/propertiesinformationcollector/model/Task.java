package com.example.propertiesinformationcollector.model;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
	private CompletableFuture<Properties> properties;
}
