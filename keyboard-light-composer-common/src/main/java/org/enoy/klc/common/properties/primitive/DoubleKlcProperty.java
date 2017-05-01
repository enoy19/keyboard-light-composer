package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcPropertyValue;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class DoubleKlcProperty extends DoubleKlcReadOnlyProperty
		implements
			KlcWritableProperty<Double> {

	public DoubleKlcProperty(String name, String description,
			boolean valueStrategyAllowed, Double defaultValue) {
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
	public void setValue(Double value) {
		super.setValue(value);
	}

	@Override
	public void setValueStrategy(ValueStrategy<Double> valueStrategy) {
		super.setValueStrategy(valueStrategy);
	}

	// @Override
	// public void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// super.setValueStrategyAllowed(valueStrategyAllowed);
	// }
	
	@Override
	public void setDefaultValue(Double defaultValue) {
		super.setDefaultValue(defaultValue);
	}

	@Override
	public KlcPropertyValue<Double> getPropertyValue() {
		return propertyValue;
	}

}
