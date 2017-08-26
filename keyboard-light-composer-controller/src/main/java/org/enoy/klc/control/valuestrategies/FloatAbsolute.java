package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Float.class)
@Name("Absolute Float")
public class FloatAbsolute implements ValueStrategy<Float>, KlcPropertyContainer {

	private static final long serialVersionUID = 4754909637821340633L;

	private FloatKlcProperty a;

	public FloatAbsolute() {
		a = new FloatKlcProperty("A", "The Absolute Value of A", true, 1f);
	}

	@Override
	public Float getValue() {
		return Math.abs(a.getValue());
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[] { a };
	}

}