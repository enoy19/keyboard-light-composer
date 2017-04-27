package org.enoy.klc.common.properties;

public class KlcPropertyValue<T> extends KlcReadOnlyPropertyValue<T> implements KlcWritablePropertyValue<T> {

	public KlcPropertyValue(Class<T> propertyValueType, T value,
			ValueStrategy<T> valueStrategy) {
		super(propertyValueType, value, valueStrategy);
	}

	@Override
	public void setValue(T value) {
		super.setValue(value);
	}
	
	@Override
	public void setValueStrategy(ValueStrategy<T> valueStrategy) {
		super.setValueStrategy(valueStrategy);
	}
	
}
