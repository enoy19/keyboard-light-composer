package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class BooleanKlcReadOnlyProperty extends KlcReadOnlyProperty<Boolean> {

	public BooleanKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, Boolean defaultValue) {
		super(Boolean.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
