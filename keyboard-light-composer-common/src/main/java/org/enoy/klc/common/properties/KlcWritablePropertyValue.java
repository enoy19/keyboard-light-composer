package org.enoy.klc.common.properties;

import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

import java.io.Serializable;

public interface KlcWritablePropertyValue<T extends Serializable>  {

	public void setValue(T value);
	public void setValueStrategy(ValueStrategy<T> valueStrategy);

}
