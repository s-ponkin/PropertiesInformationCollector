package com.example.propertiesinformationcollector.jobs;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.model.Properties;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.property.PropertyUtil;
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

	@Async
	@Scheduled(fixedRate = 5000)
	public void startingProcessAsync() {
		Task task = taskStorage.getObjectInProgress();
		if (task != null) {
			Properties properties = PropertyUtil.getProperties(task.getServices());
			task.setProperties(properties);
			task.setStatusTask(StatusTask.SUCCESS);
		}
	}
}
