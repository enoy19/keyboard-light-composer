package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class DoubleKlcReadOnlyProperty extends KlcReadOnlyProperty<Double> {

	private static final long serialVersionUID = 212028767488565525L;

	public DoubleKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, Double defaultValue) {
		super(Double.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
