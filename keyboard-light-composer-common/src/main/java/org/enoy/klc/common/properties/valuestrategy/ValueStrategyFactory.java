package org.enoy.klc.common.properties.valuestrategy;

import org.enoy.klc.common.factories.FactoryGeneric;

import java.lang.reflect.InvocationTargetException;

public class ValueStrategyFactory extends FactoryGeneric<ValueStrategy<?>>{

	public ValueStrategyFactory(Class<? extends ValueStrategy<?>> factoryType) {
		super(factoryType);
	}

	@SuppressWarnings({ "rawtypes" })
	public static ValueStrategyFactory createFactory(Class<? extends ValueStrategy> factoryType){
		try {
			return createFactory(factoryType, ValueStrategyFactory.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
}
