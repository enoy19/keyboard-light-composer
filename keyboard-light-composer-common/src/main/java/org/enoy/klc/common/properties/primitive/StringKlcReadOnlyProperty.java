package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class StringKlcReadOnlyProperty extends KlcReadOnlyProperty<String> {

	public StringKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, String defaultValue) {
		super(String.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
