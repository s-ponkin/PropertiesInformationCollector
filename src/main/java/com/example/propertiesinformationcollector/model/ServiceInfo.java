package com.example.propertiesinformationcollector.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ServiceInfo {

	String name;

	String pathToProperties;
}
