package com.lightweight.hybridcustomruntime.function;

import java.util.function.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.support.GenericMessage;

import com.lightweight.hybridcustomruntime.Application;
import com.lightweight.hybridcustomruntime.model.Country;
import com.lightweight.hybridcustomruntime.model.WorldTime;
import com.lightweight.hybridcustomruntime.service.CountryValidatorService;
import com.lightweight.hybridcustomruntime.service.WorldTimeService;

public class WorlTimeFunction implements Function<GenericMessage<Country>, GenericMessage<WorldTime>> {

	private static Log logger = LogFactory.getLog(WorlTimeFunction.class);

	private CountryValidatorService validtorService;

	private WorldTimeService worldTimeService;

	@Override
	public GenericMessage<WorldTime> apply(GenericMessage<Country> value) {
		init();
		logger.info("Processing: " + value.getPayload());
		return new GenericMessage<WorldTime>(getWorldTime(value.getPayload().getCountry()), value.getHeaders());
	}

	private WorldTime getWorldTime(String country) {
		String validity = validtorService.getValidity(country);
		logger.info("Validity: " + validity);
		if (!"Valid".equals(validity)) {
			throw new RuntimeException("Invalid Country");
		}
		logger.info("Fetching current time of Country : " + country);
		return worldTimeService.getWorldTime(country);
	}

	private void init() {
		this.validtorService = Application.getInstance().getContext().getBean(CountryValidatorService.class);
		this.worldTimeService = Application.getInstance().getContext().getBean(WorldTimeService.class);
	}
}