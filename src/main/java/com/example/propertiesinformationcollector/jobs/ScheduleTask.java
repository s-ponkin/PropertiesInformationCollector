package com.example.propertiesinformationcollector.jobs;

import com.example.propertiesinformationcollector.model.PropertiesServices;
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
			PropertiesServices propertiesServices = propertiesService.getPropertiesServices(task.getServices());
			taskStorage.updateByUuid(task.getUuid(),propertiesServices);
		}
	}
}
