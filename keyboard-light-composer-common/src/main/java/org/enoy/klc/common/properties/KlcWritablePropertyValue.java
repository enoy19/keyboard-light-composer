package org.enoy.klc.common.properties;

import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public interface KlcWritablePropertyValue<T> {

	public void setValue(T value);
	public void setValueStrategy(ValueStrategy<T> valueStrategy);

}
