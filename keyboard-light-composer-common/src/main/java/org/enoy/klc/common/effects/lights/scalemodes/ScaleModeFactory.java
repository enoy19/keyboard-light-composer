package org.enoy.klc.common.effects.lights.scalemodes;

import java.lang.reflect.InvocationTargetException;

import org.enoy.klc.common.factories.Factory;

public class ScaleModeFactory extends Factory<ScaleMode> {

	public ScaleModeFactory(Class<? extends ScaleMode> factoryType) {
		super(factoryType);
	}

	public static ScaleModeFactory createFactory(Class<? extends ScaleMode> factoryType) {
		try {
			return createFactory(factoryType, ScaleModeFactory.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
