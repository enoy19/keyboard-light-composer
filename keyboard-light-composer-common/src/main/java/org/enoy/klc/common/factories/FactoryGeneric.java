package org.enoy.klc.common.factories;

import java.lang.reflect.InvocationTargetException;

public abstract class FactoryGeneric<T> extends Factory<T> {
	
	private Class<?> factoryGenericType;

	public FactoryGeneric(Class<? extends T> factoryType) {
		super(factoryType);
	}
	
	public Class<?> getFactoryGenericType() {
		return factoryGenericType;
	}

	public void setFactoryGenericType(Class<?> factoryGenericType) {
		this.factoryGenericType = factoryGenericType;
	}

	@SuppressWarnings("unchecked")
	protected static <T, FACTORY> FACTORY createFactory(Class<T> factoryType, Class<FACTORY> factoryClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			SecurityException {
		FACTORY factory = (FACTORY) factoryClass.getConstructors()[0].newInstance(factoryType);
		setFactoryFields(factoryType, (FactoryGeneric<?>) factory);
		return factory;
	}
	
	static void setFactoryFields(Class<?> factoryType, FactoryGeneric<?> factory) {
		Factory.setFactoryFields(factoryType, factory);
		setFactoryGenericType(factoryType, factory);
	}

	private static void setFactoryGenericType(Class<?> factoryType, FactoryGeneric<?> factory) {
		
		FactoryGenericType fgt = factoryType.getAnnotation(FactoryGenericType.class);
		
		Class<?> type = null;
		
		if(fgt != null){
			type = fgt.value();
		}
		
		factory.setFactoryGenericType(type);
		
	}

}
