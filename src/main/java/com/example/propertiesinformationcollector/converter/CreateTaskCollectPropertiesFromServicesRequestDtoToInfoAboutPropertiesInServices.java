package com.example.propertiesinformationcollector.converter;

import com.example.propertiesinformationcollector.model.dto.CreateTaskCollectPropertiesFromServicesRequestDto;
import com.example.propertiesinformationcollector.model.dto.CreateTaskCollectPropertiesFromServicesRequestDto.CreateTaskCollectPropertiesFromServicesRequestServicesDto;
import com.example.propertiesinformationcollector.model.InfoAboutPropertiesInServices;
import com.example.propertiesinformationcollector.model.ServiceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CreateTaskCollectPropertiesFromServicesRequestDtoToInfoAboutPropertiesInServices implements Converter<CreateTaskCollectPropertiesFromServicesRequestDto, InfoAboutPropertiesInServices> {

	private final ConversionService conversionService;

	@Override
	public InfoAboutPropertiesInServices convert(CreateTaskCollectPropertiesFromServicesRequestDto source) {
		List<ServiceInfo> infoAboutLocationOfFileWithPropertyList = new ArrayList<>();
		for (CreateTaskCollectPropertiesFromServicesRequestServicesDto element :source.getServices()) {
			infoAboutLocationOfFileWithPropertyList.add(conversionService.convert(element, ServiceInfo.class));
		}
		return InfoAboutPropertiesInServices.builder()
			.services(infoAboutLocationOfFileWithPropertyList)
			.ignorePropertiesList(source.getIgnorePropertiesList())
			.build();
	}
}
