package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class BooleanKlcReadOnlyProperty extends KlcReadOnlyProperty<Boolean> {

	private static final long serialVersionUID = -6375748399315592212L;

	public BooleanKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, Boolean defaultValue) {
		super(Boolean.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
