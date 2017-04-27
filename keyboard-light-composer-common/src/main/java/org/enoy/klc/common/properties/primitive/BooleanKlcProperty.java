package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcPropertyValue;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.ValueStrategy;

public class BooleanKlcProperty extends BooleanKlcReadOnlyProperty
		implements
			KlcWritableProperty<Boolean> {

	public BooleanKlcProperty(String name, String description,
			boolean valueStrategyAllowed, Boolean defaultValue) {
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
	public void setValue(Boolean value) {
		super.setValue(value);
	}
	
	@Override
	public void setValueStrategy(ValueStrategy<Boolean> valueStrategy) {
		super.setValueStrategy(valueStrategy);
	}
	
	// @Override
	// public void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// super.setValueStrategyAllowed(valueStrategyAllowed);
	// }
	
	@Override
	public void setDefaultValue(Boolean defaultValue) {
		super.setDefaultValue(defaultValue);
	}
	
	@Override
	public KlcPropertyValue<Boolean> getPropertyValue() {
		return propertyValue;
	}

}
