package com.example.propertiesinformationcollector.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyInfo {

	String name;

	String value;

	String description;
}
