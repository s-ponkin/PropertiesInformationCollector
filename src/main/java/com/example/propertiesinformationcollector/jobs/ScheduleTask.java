package com.example.propertiesinformationcollector.jobs;

import com.example.propertiesinformationcollector.model.PropertyInfo;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.PropertiesService;
import com.example.propertiesinformationcollector.service.TaskStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor

public class ScheduleTask {

	private final TaskStorage taskStorage;
	private final PropertiesService propertiesService;

	@Async
	@Scheduled(fixedRate = 5000)
	public void startingProcessAsync() {
		Task task = taskStorage.findFirstTaskInProgress();
		if (task != null) {
			List<List<PropertyInfo>> propertiesServicesList = propertiesService.collectPropertiesServices(task.getServices());
			taskStorage.updateByUuid(task.getUuid(),propertiesServicesList);
		}
	}
}
