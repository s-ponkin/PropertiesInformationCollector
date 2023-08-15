package com.example.propertiesinformationcollector.jobs;

import com.example.propertiesinformationcollector.enumStatus.StatusTask;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.impl.PropertiesServiceImpl;
import com.example.propertiesinformationcollector.service.impl.TaskStorageImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.UUID;

@EnableScheduling
public class ScheduledTask {

    private static final Map<UUID, Task> taskMap = TaskStorageImpl.taskMap;
    private static PropertiesServiceImpl propertiesService;

    @Scheduled(fixedRate = 5000)
    public void startingProcess() {
        taskMap.forEach((key, value) -> {
            if (taskMap.get(key).getStatusTask() == StatusTask.IN_PROGRESS) {
                taskMap.get(key).setProperties(propertiesService.create(value.getServices()));
            }
        });
    }

    @Scheduled(fixedRate = 5000)
    public void changeStatus() {
        taskMap.forEach((key, value) -> {
            if (taskMap.get(key).getProperties().isDone()) {
                taskMap.get(key).setStatusTask(StatusTask.SUCCESS);
            }
        });
    }
}
