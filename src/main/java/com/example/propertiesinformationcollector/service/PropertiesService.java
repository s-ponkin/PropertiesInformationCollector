package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.PropertyInfo;

import java.util.List;

public interface PropertiesService {

	/**
	 * Возвращает properties всех services
	 *
	 * @param services oбъект с информацией о файлах с property
	 *
	 * @return идентификатор задачи
	 */
	List<List<PropertyInfo>> collectPropertiesServices(InfoAboutPropertiesInServices services);

}
