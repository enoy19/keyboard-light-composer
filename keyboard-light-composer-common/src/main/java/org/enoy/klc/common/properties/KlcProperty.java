package org.enoy.klc.common.properties;

import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class KlcProperty<T> extends KlcReadOnlyProperty<T>
		implements
			KlcWritableProperty<T> {

	public KlcProperty(Class<T> propertyType, String name, String description,
			boolean valueStrategyAllowed, T defaultValue) {
		super(propertyType, name, description, valueStrategyAllowed,
				defaultValue);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public void setValue(T value) {
		super.setValue(value);
	}

	@Override
	public void setValueStrategy(ValueStrategy<T> valueStrategy) {
		super.setValueStrategy(valueStrategy);
	}

	// @Override
	// public void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// super.setValueStrategyAllowed(valueStrategyAllowed);
	// }

	@Override
	public void setDefaultValue(T defaultValue) {
		super.setDefaultValue(defaultValue);
	}

	@Override
	public void setDescription(String description) {
		super.setDescription(description);
	}

	@Override
	public KlcPropertyValue<T> getPropertyValue() {
		return propertyValue;
	}

}
