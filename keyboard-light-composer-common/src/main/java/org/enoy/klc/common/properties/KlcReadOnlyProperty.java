package org.enoy.klc.common.properties;

import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public class KlcReadOnlyProperty<T> implements KlcPropertyBase<T> {

	private Class<T> propertyType;
	protected String name;
	protected String description;
	private final boolean valueStrategyAllowed;
	protected KlcPropertyValue<T> propertyValue;
	protected T defaultValue;

	public KlcReadOnlyProperty(Class<T> propertyType, String name,
			String description, boolean valueStrategyAllowed, T defaultValue) {
		
		this.propertyType = propertyType;
		this.name = name;
		this.description = description;
		this.valueStrategyAllowed = valueStrategyAllowed;
		this.propertyValue = new KlcPropertyValue<T>(propertyType, defaultValue,
				null);
		this.defaultValue = defaultValue;
	}

	@Override
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean isValueStrategyAllowed() {
		return valueStrategyAllowed;
	}

	// protected void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// this.valueStrategyAllowed = valueStrategyAllowed;
	// }

	@Override
	public T getValue() {
		T value = getPropertyValue().getValue();
		if (value == null) {
			value = getDefaultValue();
		}
		return value;
	}

	protected void setValue(T value) {
		propertyValue.setValue(value);
	}

	@Override
	public KlcReadOnlyPropertyValue<T> getPropertyValue() {
		return propertyValue;
	}

	@Override
	public T getDefaultValue() {
		return defaultValue;
	}

	protected void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	protected void setValueStrategy(ValueStrategy<T> valueStrategy) {
		propertyValue.setValueStrategy(valueStrategy);
	}

	@Override
	public Class<T> getPropertyType() {
		return propertyType;
	}

	public boolean isValueStrategyPresent() {
		return propertyValue.getValueStrategy() != null;
	}

	@Override
	public void delete() {
		propertyValue.delete();
	}

}
