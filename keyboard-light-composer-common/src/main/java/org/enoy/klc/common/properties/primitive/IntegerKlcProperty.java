package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcPropertyValue;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class IntegerKlcProperty extends IntegerKlcReadOnlyProperty implements KlcWritableProperty<Integer> {

	private static final long serialVersionUID = -1012189765181313029L;

	public IntegerKlcProperty(String name, String description,
			boolean valueStrategyAllowed, Integer defaultValue) {
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
	public void setValue(Integer value) {
		super.setValue(value);
	}
	
	@Override
	public void setValueStrategy(ValueStrategy<Integer> valueStrategy) {
		super.setValueStrategy(valueStrategy);
	}
	
	// @Override
	// public void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// super.setValueStrategyAllowed(valueStrategyAllowed);
	// }
	
	@Override
	public void setDefaultValue(Integer defaultValue) {
		super.setDefaultValue(defaultValue);
	}
	
	@Override
	public KlcPropertyValue<Integer> getPropertyValue() {
		return propertyValue;
	}

}
