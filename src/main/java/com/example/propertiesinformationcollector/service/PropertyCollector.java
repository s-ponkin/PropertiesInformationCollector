package com.example.propertiesinformationcollector.service;

import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.ServiceInfo;

import java.util.List;

public interface PropertyCollector {
	List<PropertyInfo> collectPropertyFromService(ServiceInfo serviceInfo);
}
