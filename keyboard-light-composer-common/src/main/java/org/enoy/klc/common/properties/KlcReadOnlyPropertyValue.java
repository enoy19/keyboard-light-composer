package org.enoy.klc.common.properties;

import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class KlcReadOnlyPropertyValue<T> {

	private Class<T> propertyValueType;
	protected T value;
	protected ValueStrategy<T> valueStrategy;

	public KlcReadOnlyPropertyValue(Class<T> propertyValueType, T value,
			ValueStrategy<T> valueStrategy) {
		this.propertyValueType = propertyValueType;
		this.value = value;
		this.valueStrategy = valueStrategy;
	}

	public KlcReadOnlyPropertyValue(Class<T> propertyValueType, T value) {
		this(propertyValueType, value, null);
	}

	public KlcReadOnlyPropertyValue(Class<T> propertyValueType) {
		this(propertyValueType, null);
	}

	public T getValue() {
		if (valueStrategy != null) {
			return valueStrategy.getValue();
		}
		return value;
	}

	public ValueStrategy<T> getValueStrategy() {
		return valueStrategy;
	}

	protected void setValue(T value) {
		this.value = value;
	}

	protected void setValueStrategy(ValueStrategy<T> valueStrategy) {
		this.valueStrategy = valueStrategy;
	}

	public Class<T> getPropertyValueType() {
		return propertyValueType;
	}

}
