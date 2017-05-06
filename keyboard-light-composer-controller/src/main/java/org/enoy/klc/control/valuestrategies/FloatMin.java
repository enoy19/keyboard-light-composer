package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Float.class)
@Name("Minumum Float")
public class FloatMin implements ValueStrategy<Float>, KlcPropertyContainer {

	private static final long serialVersionUID = -7091891487390158090L;

	private FloatKlcProperty a;
	private FloatKlcProperty b;

	public FloatMin() {
		a = new FloatKlcProperty("A", "First Value. Minumum of A and B", true, 1f);
		b = new FloatKlcProperty("B", "Second Value. Minumum of A and B", true, 1f);
	}

	@Override
	public Float getValue() {
		return Math.min(a.getValue(), b.getValue());
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[] { a, b };
	}

}