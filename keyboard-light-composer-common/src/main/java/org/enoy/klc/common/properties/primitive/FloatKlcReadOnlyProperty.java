package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class FloatKlcReadOnlyProperty extends KlcReadOnlyProperty<Float> {

	private static final long serialVersionUID = -6991065510760003574L;

	public FloatKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, Float defaultValue) {
		super(Float.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
