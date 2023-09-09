package com.example.propertiesinformationcollector.converter;

import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.dto.GetPropertiesFromServicesDto;
import com.example.propertiesinformationcollector.model.dto.GetPropertiesFromServicesDto.Property;
import com.example.propertiesinformationcollector.model.dto.GetPropertiesFromServicesDto.Property.ServicesValue;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListListPropertyInfoToGetPropertiesFromServicesDto implements Converter<List<List<PropertyInfo>>, GetPropertiesFromServicesDto> {

	private static final String PROPERTY_APP_NAME = "app.name";

	@Override
	public GetPropertiesFromServicesDto convert(@NonNull List<List<PropertyInfo>> source) {
		Set<String> uniquePropertyNames = getUniquePropertyNames(source);
		List<Property> propertyInfoList = new ArrayList<>();
		for (String nameProperty: uniquePropertyNames) {
			List<ServicesValue> servicesValueList = getServicesValueListByPropertyName(nameProperty, source);
			boolean allValuesEqual = true;
			String value = servicesValueList.get(0).getValue();
			for (ServicesValue servicesValue : servicesValueList) {
				if (!value.equals(servicesValue.getValue())) {
					allValuesEqual = false;
					break;
				}
			}
            propertyInfoList.add(Property.builder()
				.name(nameProperty)
				.servicesValue(servicesValueList)
				.allValuesEqual(allValuesEqual)
				.build());
		}
		return new GetPropertiesFromServicesDto(propertyInfoList);
	}

	private static Set<String> getUniquePropertyNames(List<List<PropertyInfo>> allListWithPropertyList) {
		Set<String> uniquePropertyNames = new HashSet<>();
		for (List<PropertyInfo> listPropertyInfo : allListWithPropertyList) {
			listPropertyInfo.forEach(element -> uniquePropertyNames.add(element.getName()));
		}
		return uniquePropertyNames;
	}

	private static List<ServicesValue> getServicesValueListByPropertyName(String propertyName, List<List<PropertyInfo>> allListWithPropertyList){
		List<ServicesValue> servicesValueList = new ArrayList<>();
		for (List<PropertyInfo> listPropertyInfo : allListWithPropertyList) {
			String serviceName = Objects.requireNonNull(listPropertyInfo.stream().filter(element -> element.getName().equals(PROPERTY_APP_NAME)).findFirst().orElse(null)).getValue();
			for (PropertyInfo propertyInfo : listPropertyInfo) {
				if(propertyName.equals(propertyInfo.getName())){
					ServicesValue servicesValue = ServicesValue.builder()
						.description(propertyInfo.getDescription())
						.service(serviceName)
						.value(propertyInfo.getValue())
						.build();
					servicesValueList.add(servicesValue);
				}
			}
		}
		return servicesValueList;
	}
}
