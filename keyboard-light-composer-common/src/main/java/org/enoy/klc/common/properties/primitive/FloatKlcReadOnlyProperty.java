package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class FloatKlcReadOnlyProperty extends KlcReadOnlyProperty<Float> {

	public FloatKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, Float defaultValue) {
		super(Float.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
