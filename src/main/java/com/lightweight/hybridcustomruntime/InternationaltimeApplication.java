package com.lightweight.hybridcustomruntime;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import com.lightweight.hybridcustomruntime.function.WorlTimeFunction;
import com.lightweight.hybridcustomruntime.model.Country;
import com.lightweight.hybridcustomruntime.model.WorldTime;

@SpringBootApplication
public class InternationaltimeApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	public static void main(String[] args) {
		FunctionalSpringApplication.run(InternationaltimeApplication.class, args);
	}

	@Override
	public void initialize(GenericApplicationContext context) {

		context.registerBean("worldTimeFunction", FunctionRegistration.class,
				() -> new FunctionRegistration<>(new WorlTimeFunction())
						.type(FunctionType.from(Country.class).to(WorldTime.class).message()));

		Application.getInstance().setContext(context);
	}
}