package org.enoy.klc.common.properties.valuestrategy;

public class ValueStrategyFactory<T> {

	private Class<ValueStrategy<T>> valueStrategyClass;
	private Class<T> valueType;
	private String name;

	public ValueStrategyFactory(Class<ValueStrategy<T>> valueStrategyClass, Class<T> valueType, String name) {
		this.valueStrategyClass = valueStrategyClass;
		this.valueType = valueType;
		this.name = name;
	}

	public ValueStrategy<T> createValueStrategy() {
		try {
			return valueStrategyClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Class<T> getValueType() {
		return valueType;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ValueStrategyFactory<?> createValueStrategyFactory(Class<? extends ValueStrategy> clazz) {
		ValueStrategyType valueTypeAnnotation = clazz.getAnnotation(ValueStrategyType.class);

		if (valueTypeAnnotation == null) {
			throw new RuntimeException(
					ValueStrategyType.class.getSimpleName() + " not present for: " + clazz.getName());
		}

		String name;
		ValueStrategyName nameAnnotation = clazz.getAnnotation(ValueStrategyName.class);

		if (nameAnnotation == null || (name = nameAnnotation.value()) == null || name.trim().isEmpty()) {
			name = clazz.getSimpleName();
		}

		ValueStrategyFactory<?> factory = new ValueStrategyFactory(clazz, valueTypeAnnotation.value(), name);

		return factory;

	}

	@Override
	public String toString() {
		return name;
	}

}
