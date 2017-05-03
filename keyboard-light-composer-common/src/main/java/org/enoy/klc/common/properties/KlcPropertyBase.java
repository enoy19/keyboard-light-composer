package org.enoy.klc.common.properties;

import org.enoy.klc.common.Deletable;

public interface KlcPropertyBase<T> extends Deletable {

	public String getName();
	public String getDescription();
	public boolean isValueStrategyAllowed();
	public T getValue();
	public KlcReadOnlyPropertyValue<T> getPropertyValue();
	public T getDefaultValue();
	public Class<T> getPropertyType();
	public boolean isValueStrategyPresent();
	
}
