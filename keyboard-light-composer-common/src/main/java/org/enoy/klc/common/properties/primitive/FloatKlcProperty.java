package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcPropertyValue;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class FloatKlcProperty extends FloatKlcReadOnlyProperty
		implements
			KlcWritableProperty<Float> {

	public FloatKlcProperty(String name, String description,
			boolean valueStrategyAllowed, Float defaultValue) {
		super(name, description, valueStrategyAllowed, defaultValue);
	}

	@Override
	public void setDescription(String description) {
		super.setDescription(description);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public void setValue(Float value) {
		super.setValue(value);
	}

	@Override
	public void setValueStrategy(ValueStrategy<Float> valueStrategy) {
		super.setValueStrategy(valueStrategy);
	}

	// @Override
	// public void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// super.setValueStrategyAllowed(valueStrategyAllowed);
	// }

	@Override
	public void setDefaultValue(Float defaultValue) {
		super.setDefaultValue(defaultValue);
	}

	@Override
	public KlcPropertyValue<Float> getPropertyValue() {
		return propertyValue;
	}

}
