package org.enoy.klc.common.properties.primitive;

import org.enoy.klc.common.properties.KlcReadOnlyProperty;

public class IntegerKlcReadOnlyProperty extends KlcReadOnlyProperty<Integer> {

	private static final long serialVersionUID = 8383677129515666720L;

	public IntegerKlcReadOnlyProperty(String name, String description,
			boolean valueStrategyAllowed, Integer defaultValue) {
		super(Integer.class, name, description, valueStrategyAllowed,
				defaultValue);
	}

}
