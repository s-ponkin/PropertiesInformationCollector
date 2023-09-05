package com.example.propertiesinformationcollector.service.impl;

import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.Task;
import com.example.propertiesinformationcollector.service.TaskStorage;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class TaskStorageImplTest {

	private static InfoAboutPropertiesInServices services;
	private static TaskStorage taskStorage;

	@BeforeTestClass
	void init(){
		Map<UUID, Task> taskMap = new HashMap<>();
		services = InfoAboutPropertiesInServices.builder().build();
	}

	@Test
	void createTest() {
		taskStorage.create(services);
	}

	@Test
	void getByUuidTest() {
	}

	@Test
	void updateByUuidTest() {
	}

	@Test
	void findFirstTaskInProgressTest() {
	}
}