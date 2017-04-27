package org.enoy.klc.common.properties;

public interface KlcWritablePropertyValue<T> {

	public void setValue(T value);
	public void setValueStrategy(ValueStrategy<T> valueStrategy);

}
