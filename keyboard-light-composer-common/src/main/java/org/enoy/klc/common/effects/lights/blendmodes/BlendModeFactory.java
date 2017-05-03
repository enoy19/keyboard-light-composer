package org.enoy.klc.common.effects.lights.blendmodes;

import java.lang.reflect.InvocationTargetException;

import org.enoy.klc.common.factories.Factory;

public class BlendModeFactory extends Factory<BlendMode> {

	public BlendModeFactory(Class<? extends BlendMode> factoryType) {
		super(factoryType);
	}

	public static BlendModeFactory createFactory(Class<? extends BlendMode> factoryType) {
		try {
			return createFactory(factoryType, BlendModeFactory.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
