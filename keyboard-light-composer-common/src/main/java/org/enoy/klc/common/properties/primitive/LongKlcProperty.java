package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcPropertyValue;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class LongKlcProperty extends LongKlcReadOnlyProperty implements KlcWritableProperty<Long> {

	public LongKlcProperty(String name, String description,
			boolean valueStrategyAllowed, Long defaultValue) {
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
	public void setValue(Long value) {
		super.setValue(value);
	}
	
	@Override
	public void setValueStrategy(ValueStrategy<Long> valueStrategy) {
		super.setValueStrategy(valueStrategy);
	}
	
	// @Override
	// public void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// super.setValueStrategyAllowed(valueStrategyAllowed);
	// }
	
	@Override
	public void setDefaultValue(Long defaultValue) {
		super.setDefaultValue(defaultValue);
	}
	
	@Override
	public KlcPropertyValue<Long> getPropertyValue() {
		return propertyValue;
	}

}
