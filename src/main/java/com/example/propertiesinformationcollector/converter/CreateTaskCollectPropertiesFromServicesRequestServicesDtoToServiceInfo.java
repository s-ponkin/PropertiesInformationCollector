package com.example.propertiesinformationcollector.converter;

import com.example.propertiesinformationcollector.model.ServiceInfo;
import org.springframework.core.convert.converter.Converter;
import com.example.propertiesinformationcollector.model.dto.CreateTaskCollectPropertiesFromServicesRequestDto.CreateTaskCollectPropertiesFromServicesRequestServicesDto;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskCollectPropertiesFromServicesRequestServicesDtoToServiceInfo implements Converter<CreateTaskCollectPropertiesFromServicesRequestServicesDto, ServiceInfo> {

	@Override
	public ServiceInfo convert(CreateTaskCollectPropertiesFromServicesRequestServicesDto source) {
		return ServiceInfo.builder()
			.name(source.getName())
			.pathToProperties(source.getPath())
			.build();
	}
}
