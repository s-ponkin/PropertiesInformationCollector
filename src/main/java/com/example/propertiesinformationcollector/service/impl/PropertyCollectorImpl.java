package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.ServiceInfo;
import com.example.propertiesinformationcollector.service.PropertyCollector;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PropertyCollectorImpl implements PropertyCollector {

	private static final String CHARACTER_OF_TRANSITION_TO_NEW_LINE = "\\n";
	private static final String CHARACTER_EQUAL = "=";
	private static final String CHARACTER_GRID = "#";
	private static final Logger logger = Logger.getLogger(PropertyCollectorImpl.class.getName());

	@Override
	public List<PropertyInfo> collectPropertyFromService(ServiceInfo serviceInfo) {
		List<PropertyInfo> propertyInfoList = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(serviceInfo.getPathToProperties()))) {
			String str;
			StringJoiner description = new StringJoiner(CHARACTER_OF_TRANSITION_TO_NEW_LINE);
			while ((str = in.readLine()) != null) {
				if (str.indexOf(CHARACTER_GRID) == 0) {
					description.add(str);
				}
				if (str.contains(CHARACTER_EQUAL)) {
					PropertyInfo info = PropertyInfo.builder()
						.name(str.substring(0, str.indexOf(CHARACTER_EQUAL)))
						.value(str.substring(str.indexOf(CHARACTER_EQUAL) + 1))
						.build();

					if (description.length() != 0) {
						info.setDescription(description.toString());
					}
					propertyInfoList.add(info);
					description = new StringJoiner(CHARACTER_OF_TRANSITION_TO_NEW_LINE);
				}
			}

		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
			throw new RuntimeException(e);
		}
		return propertyInfoList;
	}
}
