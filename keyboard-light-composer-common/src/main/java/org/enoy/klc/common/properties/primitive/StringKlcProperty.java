package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcPropertyValue;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class StringKlcProperty extends StringKlcReadOnlyProperty implements KlcWritableProperty<String> {

	private static final long serialVersionUID = -910762936425272680L;

	public StringKlcProperty(String name, String description,
			boolean valueStrategyAllowed, String defaultValue) {
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
	public void setValue(String value) {
		super.setValue(value);
	}
	
	@Override
	public void setValueStrategy(ValueStrategy<String> valueStrategy) {
		super.setValueStrategy(valueStrategy);
	}
	
	// @Override
	// public void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// super.setValueStrategyAllowed(valueStrategyAllowed);
	// }
	
	@Override
	public void setDefaultValue(String defaultValue) {
		super.setDefaultValue(defaultValue);
	}
	
	@Override
	public KlcPropertyValue<String> getPropertyValue() {
		return propertyValue;
	}

}
