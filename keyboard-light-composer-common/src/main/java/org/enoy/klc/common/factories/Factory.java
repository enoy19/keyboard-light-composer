package org.enoy.klc.common.factories;

import java.lang.reflect.InvocationTargetException;

public abstract class Factory<T> {

	private Class<? extends T> factoryType;
	private String name;
	private String group;

	public Factory(Class<? extends T> factoryType) {
		this.factoryType = factoryType;
	}

	public T create() {
		try {
			return factoryType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<? extends T> getFactoryType() {
		return factoryType;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@SuppressWarnings("unchecked")
	protected static <T, FACTORY> FACTORY createFactory(Class<T> factoryType, Class<FACTORY> factoryClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			SecurityException {
		FACTORY factory = (FACTORY) factoryClass.getConstructors()[0].newInstance(factoryType);
		setFactoryFields(factoryType, (Factory<?>) factory);
		return factory;
	}

	static <T> void setFactoryFields(Class<?> factoryType, Factory<?> factory) {
		setFactoryName(factoryType, factory);
		setFactoryGroup(factoryType, factory);
	}

	private static void setFactoryName(Class<?> factoryType, Factory<?> factory) {
		Name nameAnnotation = factoryType.getAnnotation(Name.class);
		String name;
		if (nameAnnotation == null || (name = nameAnnotation.value()).trim().isEmpty()) {
			name = factoryType.getSimpleName();
		}
		factory.setName(name);
	}

	private static void setFactoryGroup(Class<?> factoryType, Factory<?> factory) {
		Group groupAnnotation = factoryType.getAnnotation(Group.class);
		String group;
		if (groupAnnotation == null || (group = groupAnnotation.value()).trim().isEmpty()) {
			group = factoryType.getSimpleName();
		}
		factory.setGroup(group);
	}

	@Override
	public String toString() {
		return getName();
	}

}
