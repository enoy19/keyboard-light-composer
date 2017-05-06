package org.enoy.klc.common.properties;

import java.io.Serializable;

import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class KlcPropertyValue<T extends Serializable> extends KlcReadOnlyPropertyValue<T>
		implements KlcWritablePropertyValue<T> {

	private static final long serialVersionUID = -3134347686809186984L;

	public KlcPropertyValue(Class<T> propertyValueType, T value, ValueStrategy<T> valueStrategy) {
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
