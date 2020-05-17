package com.lightweight.hybridcustomruntime;

import org.springframework.context.support.GenericApplicationContext;

public final class Application {

	private static volatile Application INSTANCE = null;

	private GenericApplicationContext context;

	public static synchronized Application getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new Application();
			return INSTANCE;
		}
		return INSTANCE;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	protected Object readResolve() {
		return INSTANCE;
	}

	public GenericApplicationContext getContext() {
		return this.context;
	}

	void setContext(GenericApplicationContext context) {
		this.context = context;
	}
}