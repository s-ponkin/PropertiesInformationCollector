package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.model.dto.GetPropertiesFromServicesDto.Property;
import com.example.propertiesinformationcollector.model.dto.GetPropertiesFromServicesDto.Property.ServicesValue;
import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.ServiceInfo;
import com.example.propertiesinformationcollector.service.PropertiesService;
import com.example.propertiesinformationcollector.service.PropertyCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PropertiesServiceImpl implements PropertiesService {

	private final PropertyCollector propertyCollector;

	@Override
	public List<List<PropertyInfo>> collectPropertiesServices(InfoAboutPropertiesInServices services) {
		List<List<PropertyInfo>> allListWithPropertyList = new ArrayList<>();
		for (ServiceInfo info: services.getServices()) {
			allListWithPropertyList.add(propertyCollector.collectPropertyFromService(info));
		}
		return allListWithPropertyList;
	}

	private static List<Property> getUniquePropertiesOfEachServiceWithValue(List<Map<String, ServicesValue>> propertiesServicesMapsList, Set<String> uniquePropertyNames){
		List<Property> propertyList = new ArrayList<>();

		for (String nameProperty : uniquePropertyNames) {
			Property property = Property.builder()
				.name(nameProperty)
				.build();
			List<ServicesValue> servicesValueList = new ArrayList<>();

			for (Map<String, ServicesValue> mapsProperty : propertiesServicesMapsList) {
				if (mapsProperty.containsKey(nameProperty)) {
					ServicesValue servicesValue = mapsProperty.get(nameProperty);
					servicesValueList.add(servicesValue);
				}
			}
			property.setServicesValueList(servicesValueList);

			boolean equal = true;
			String value = servicesValueList.get(0).getValue();
			for (ServicesValue servicesValue : servicesValueList) {
				if (!value.equals(servicesValue.getValue())) {
					equal = false;
					break;
				}
			}
			property.setAllValuesEqual(equal);
			propertyList.add(property);
		}
		return propertyList;
	}

	private static Set<String> getUniquePropertyNames(List<Map<String, ServicesValue>> mapList) {
		Set<String> uniquePropertyNames = new HashSet<>();
		for (Map<String, ServicesValue> stringPropertyInfoMap : mapList) {
			uniquePropertyNames.addAll(stringPropertyInfoMap.keySet());
		}
		return uniquePropertyNames;
	}
}
