package org.enoy.klc.common.properties.valuestrategy;

public class ValueStrategyFactory<T> {
	
	private Class<ValueStrategy<T>> valueStrategyClass;
	private Class<T> valueType;
	
	public ValueStrategyFactory(Class<ValueStrategy<T>> valueStrategyClass,
			Class<T> valueType) {
		this.valueStrategyClass = valueStrategyClass;
		this.valueType = valueType;
	}

	public ValueStrategy<T> createValueStrategy(){
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
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static ValueStrategyFactory<?> createValueStrategyFactory(
			Class<? extends ValueStrategy> clazz) {

		ValueStrategyType valueTypeAnnotation = clazz
				.getAnnotation(ValueStrategyType.class);

		if (valueTypeAnnotation == null) {
			throw new RuntimeException(ValueStrategyType.class.getSimpleName()
					+ " not present for: " + clazz.getName());
		}

		ValueStrategyFactory<?> factory = new ValueStrategyFactory(clazz,
				valueTypeAnnotation.value());

		return factory;

	}

}
