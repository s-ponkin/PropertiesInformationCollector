package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.model.Properties;
import com.example.propertiesinformationcollector.model.Services;

import java.util.UUID;

public interface PropertiesService {

    /**
     * Создает задачу для получение списка настроек и вернуть идентификатор задачи
     * @param services сервисы с информацией о настройках
     * @return идентификатор задачи
     */
    UUID createTask(Services services);

    /**
     * Получить статус выполнения задачи по идентификатору задачи uuid
     * @param uuid uuid задачи
     * @return статус выполнения задачи
     */
    StatusTask getStatus(UUID uuid);

    /**
     * Получить результат выполнения задачи по идентификатору задачи uuid
     * @param uuid uuid задачи
     * @return результат выполнения задачи
     */
    Properties getResult(UUID uuid);
}
