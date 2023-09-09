package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.PropertyInfo;

import java.util.List;

public interface PropertiesService {

	/**
	 * Возвращает properties всех сервисов
	 *
	 * @param infoAboutPropertiesInServices oбъект с информацией о файлах с property
	 *
	 * @return Список списков с информацией о проперти всех сервисов
	 */
	List<List<PropertyInfo>> collectPropertiesServices(InfoAboutPropertiesInServices infoAboutPropertiesInServices);

}
