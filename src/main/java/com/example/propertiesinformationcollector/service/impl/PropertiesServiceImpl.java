package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.ServiceInfo;
import com.example.propertiesinformationcollector.service.PropertiesService;
import com.example.propertiesinformationcollector.service.PropertyCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class PropertiesServiceImpl implements PropertiesService {

	private final PropertyCollector propertyCollector;
	private static final Logger logger = Logger.getLogger(PropertiesServiceImpl.class.getName());

	@Override
	public List<List<PropertyInfo>> collectPropertiesServices(InfoAboutPropertiesInServices services) {
		List<List<PropertyInfo>> allListWithPropertyList = new ArrayList<>();
		List<Callable<List<PropertyInfo>>> callableTasks = new ArrayList<>();

		for (ServiceInfo info: services.getServices()) {
			Callable<List<PropertyInfo>> callableTask =
				() -> propertyCollector.collectPropertyFromService(info);
			callableTasks.add(callableTask);
		}

		ExecutorService service = Executors.newSingleThreadExecutor();
		List<Future<List<PropertyInfo>>> resultListFuture;
		try {
			resultListFuture = service.invokeAll(callableTasks);
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, e.getMessage());
			throw new RuntimeException(e);
		}
		service.shutdown();

		for (Future<List<PropertyInfo>> info: resultListFuture) {
			if(info.isDone()){
				try {
					allListWithPropertyList.add(info.get());
				} catch (InterruptedException | ExecutionException e) {
					logger.log(Level.WARNING, e.getMessage());
				}
			}
		}
		return allListWithPropertyList;
	}
}
