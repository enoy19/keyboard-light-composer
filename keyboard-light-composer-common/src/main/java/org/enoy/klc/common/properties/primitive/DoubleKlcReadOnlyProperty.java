package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class DoubleKlcReadOnlyProperty extends KlcReadOnlyProperty<Double> {

	public DoubleKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, Double defaultValue) {
		super(Double.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
