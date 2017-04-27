package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class LongKlcReadOnlyProperty extends KlcReadOnlyProperty<Long> {

	public LongKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, Long defaultValue) {
		super(Long.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
