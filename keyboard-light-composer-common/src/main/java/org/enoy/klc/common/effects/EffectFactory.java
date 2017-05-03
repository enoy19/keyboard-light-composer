package org.enoy.klc.common.effects;

import java.lang.reflect.InvocationTargetException;

import org.enoy.klc.common.factories.Factory;

public class EffectFactory extends Factory<Effect> {

	public EffectFactory(Class<? extends Effect> factoryType) {
		super(factoryType);
	}

	public static EffectFactory createFactory(Class<? extends Effect> factoryType) {
		try {
			return Factory.createFactory(factoryType, EffectFactory.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
