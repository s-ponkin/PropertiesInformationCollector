package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.PropertiesServices;
import com.example.propertiesinformationcollector.model.Services;

public interface PropertiesService {

	/**
	 * Возвращает properties всех services
	 *
	 * @param services oбъект с информацией о файлах с property
	 *
	 * @return идентификатор задачи
	 */
	PropertiesServices getPropertiesServices(Services services);

}
