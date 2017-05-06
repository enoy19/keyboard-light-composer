package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Boolean.class)
@Name("Float Greater Than")
public class FloatGreaterThan implements ValueStrategy<Boolean>, KlcPropertyContainer {

	private static final long serialVersionUID = -8734809933004496435L;
	
	private FloatKlcProperty a;
	private FloatKlcProperty b;

	public FloatGreaterThan() {
		a = new FloatKlcProperty("A", "A Value. A > B", true, 1f);
		b = new FloatKlcProperty("B", "B Value. A > B", true, 1f);
	}

	@Override
	public Boolean getValue() {
		return a.getValue() > b.getValue();
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[] { a, b };
	}

}