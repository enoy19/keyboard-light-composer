package org.enoy.klc.common.properties;

import java.io.Serializable;

import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

public interface KlcWritablePropertyValue<T extends Serializable>  {

	public void setValue(T value);
	public void setValueStrategy(ValueStrategy<T> valueStrategy);

}
