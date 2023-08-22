package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.model.*;
import com.example.propertiesinformationcollector.model.PropertiesServices;
import com.example.propertiesinformationcollector.service.PropertiesService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PropertiesServiceImpl implements PropertiesService {

	private static final String CHARACTER_OF_TRANSITION_TO_NEW_LINE = "\\n";
	private static final String CHARACTER_EQUAL = "=";
	private static final String CHARACTER_GRID = "#";
	private static final String PROPERTY_APP_NAME = "app.name";
	private static final Logger logger = Logger.getLogger(PropertiesServiceImpl.class.getName());

	@Override
	public PropertiesServices getPropertiesServices(Services services) {
		List<Map<String, ServicesValue>> propertiesServiceMapsList = getPropertiesServiceMapsList(services);
		Set<String> uniquePropertyNames = getUniquePropertyNames(propertiesServiceMapsList);
		List<Property> propertiesServicesList = getPropertiesServicesList(propertiesServiceMapsList, uniquePropertyNames);
		return new PropertiesServices(propertiesServicesList);
	}

	private static List<Property> getPropertiesServicesList(List<Map<String, ServicesValue>> propertiesServicesMapsList, Set<String> uniquePropertyNames){
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

	private static List<Map<String, ServicesValue>> getPropertiesServiceMapsList(Services services) {
		List<Map<String, ServicesValue>> mapsList = new ArrayList<>();
		List<String> pathList = getPathToPropertiesFileList(services);
		List<Callable<Map<String, ServicesValue>>> callableTasks = new ArrayList<>();
		List<String> ignorePropertiesList = services.getIgnorePropertiesList();

		for (int i = 0; i < pathList.size(); i++) {
			int finalI = i;
			Callable<Map<String, ServicesValue>> callableTask =
				() -> getServicesValueMap(pathList.get(finalI), ignorePropertiesList);
			callableTasks.add(callableTask);
		}

		ExecutorService service = Executors.newSingleThreadExecutor();
		List<Future<Map<String, ServicesValue>>> resultListFuture;
		try {
			resultListFuture = service.invokeAll(callableTasks);
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, e.getMessage());
			throw new RuntimeException(e);
		}
		service.shutdown();

		for (Future<Map<String, ServicesValue>> mapFuture : resultListFuture) {
			if (mapFuture.isDone()) {
				try {
					mapsList.add(mapFuture.get());
				} catch (InterruptedException | ExecutionException e) {
					logger.log(Level.WARNING, e.getMessage());
				}
			}
		}
		return mapsList;
	}

	private static Map<String, ServicesValue> getServicesValueMap(String path, List<String> ignorePropertiesList) {
		Map<String, ServicesValue> mapProperties = new HashMap<>();
		try (BufferedReader in = new BufferedReader(new FileReader(path))) {
			String str;
			StringJoiner description = new StringJoiner(CHARACTER_OF_TRANSITION_TO_NEW_LINE);
			while ((str = in.readLine()) != null) {
				if (str.indexOf(CHARACTER_GRID) == 0) {
					description.add(str);
				}
				if (str.contains(CHARACTER_EQUAL)) {
					ServicesValue info = ServicesValue.builder()
						.value(str.substring(str.indexOf(CHARACTER_EQUAL) + 1))
						.build();
					String name = str.substring(0, str.indexOf(CHARACTER_EQUAL));
					if (description.length() != 0) {
						info.setDescription(description.toString());
					}
					if (!ignorePropertiesList.contains(name)) {
						mapProperties.put(name, info);
					}
					description = new StringJoiner(CHARACTER_OF_TRANSITION_TO_NEW_LINE);
				}
			}
			mapProperties.replaceAll((k, v) -> {
				v.setServiceName(mapProperties.get(PROPERTY_APP_NAME).getValue());
				return v;
			});
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
			throw new RuntimeException(e);
		}
		return mapProperties;
	}

	private static Set<String> getUniquePropertyNames(List<Map<String, ServicesValue>> mapList) {
		Set<String> uniquePropertyNames = new HashSet<>();
		for (Map<String, ServicesValue> stringPropertyInfoMap : mapList) {
			uniquePropertyNames.addAll(stringPropertyInfoMap.keySet());
		}
		return uniquePropertyNames;
	}

	private static List<String> getPathToPropertiesFileList(Services services) {
		List<String> pathList = new ArrayList<>();
		services.getServices().forEach(element -> pathList.add(element.getPath()));
		return pathList;
	}
}
