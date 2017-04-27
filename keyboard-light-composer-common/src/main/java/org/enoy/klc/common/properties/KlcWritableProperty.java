package org.enoy.klc.common.properties;

public interface KlcWritableProperty<T> extends KlcPropertyBase<T> {

	public void setName(String name);

	// public void setValueStrategyAllowed(boolean valueStrategyAllowed);
	
	public void setDescription(String description);

	public void setValueStrategy(ValueStrategy<T> valueStrategy);

	public void setDefaultValue(T defaultValue);

	public void setValue(T value);

	public KlcPropertyValue<T> getPropertyValue();

}
