package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.ServiceInfo;

import java.util.List;

public interface PropertyCollector {

	/**
	 * Возвращает properties сервиса
	 *
	 * @param serviceInfo oбъект с информацией о сервисе
	 *
	 * @return Список с информацией о проперти сервиса
	 */
	List<PropertyInfo> collectPropertyFromService(ServiceInfo serviceInfo);
}
