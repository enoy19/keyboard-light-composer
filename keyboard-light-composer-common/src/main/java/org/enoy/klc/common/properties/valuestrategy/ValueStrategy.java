package org.enoy.klc.common.properties.valuestrategy;

import java.io.Serializable;

public interface ValueStrategy<T extends Serializable> extends Serializable {

	public T getValue();

}
