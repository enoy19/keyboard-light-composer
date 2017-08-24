package org.enoy.klc.common.effects.lights.scalemodes;

import org.enoy.klc.common.factories.Factory;

import java.lang.reflect.InvocationTargetException;

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
