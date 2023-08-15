package com.example.propertiesinformationcollector.property;

import com.example.propertiesinformationcollector.model.*;
import com.example.propertiesinformationcollector.model.Properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyUtil {

	private static Services SERVICES;
	private static List<String> IGNORE_PROPERTIES_LIST;
	private static final Logger logger = Logger.getLogger(PropertyUtil.class.getName());


	/**
	 * Преобразует объект Services в объект Properties
	 * @param services объект Services
	 * @return объект Properties
	 */
	public static Properties getProperties(Services services) {
		initialServices(services);
		List<Map<String, ServicesValue>> mapsProperties = getMapsProperties();
		Set<String> propertiesSet = getPropertiesSet(mapsProperties);
		List<Property> properties = new ArrayList<>();

		for (String nameProperty : propertiesSet) {
			Property property = Property.builder()
				.name(nameProperty)
				.build();
			List<ServicesValue> servicesValueList = new ArrayList<>();

			for (Map<String, ServicesValue> mapsProperty : mapsProperties) {
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
			properties.add(property);
		}
		return new Properties(properties);
	}

	private static List<Map<String, ServicesValue>> getMapsProperties() {
		List<Map<String, ServicesValue>> result = new ArrayList<>();
		List<String> pathList = getPropertyPathList();
		List<Callable<Map<String, ServicesValue>>> callableTasks = new ArrayList<>();

		for (int i = 0; i < pathList.size(); i++) {
			int finalI = i;
			Callable<Map<String, ServicesValue>> callableTask = () -> getMapServicesValue(pathList.get(finalI));
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
					result.add(mapFuture.get());
				} catch (InterruptedException | ExecutionException e) {
					logger.log(Level.WARNING, e.getMessage());
				}
			}
		}
		return result;
	}

	private static Map<String, ServicesValue> getMapServicesValue(String path) {
		Map<String, ServicesValue> mapProperties = new HashMap<>();
		try (BufferedReader in = new BufferedReader(new FileReader(path))) {
			String str;
			StringJoiner description = new StringJoiner("\\n");
			while ((str = in.readLine()) != null) {
				if (str.indexOf("#") == 0) {
					description.add(str);
				}
				if (str.contains("=")) {
					ServicesValue info = ServicesValue.builder()
						.value(str.substring(str.indexOf("=") + 1))
						.build();
					String name = str.substring(0, str.indexOf("="));
					if (description.length() != 0) {
						info.setDescription(description.toString());
					}
					if (!IGNORE_PROPERTIES_LIST.contains(name)) {
						mapProperties.put(name, info);
					}
					description = new StringJoiner("\\n");
				}
			}
			mapProperties.replaceAll((k, v) -> {
				v.setServiceName(mapProperties.get("app.name").getValue());
				return v;
			});
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
			throw new RuntimeException(e);
		}
		return mapProperties;
	}

	private static Set<String> getPropertiesSet(List<Map<String, ServicesValue>> mapList) {
		Set<String> propertiesSet = new HashSet<>();
		for (Map<String, ServicesValue> stringPropertyInfoMap : mapList) {
			propertiesSet.addAll(stringPropertyInfoMap.keySet());
		}
		return propertiesSet;
	}

	private static void initialServices(Services services) {
		SERVICES = services;
		IGNORE_PROPERTIES_LIST = SERVICES.getIgnorePropertiesList();
	}

	private static List<String> getPropertyPathList() {
		List<String> pathList = new ArrayList<>();
		SERVICES.getServices().forEach(element -> pathList.add(element.getPath()));
		return pathList;
	}
}
