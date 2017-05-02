package org.enoy.klc.common.properties;

public interface KlcPropertyBase<T> {

	public String getName();
	public String getDescription();
	public boolean isValueStrategyAllowed();
	public T getValue();
	public KlcReadOnlyPropertyValue<T> getPropertyValue();
	public T getDefaultValue();
	public Class<T> getPropertyType();
	public boolean isValueStrategyPresent();
	
}
