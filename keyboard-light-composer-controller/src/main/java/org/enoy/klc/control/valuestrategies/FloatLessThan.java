package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Boolean.class)
@Name("Float Less Than")
public class FloatLessThan implements ValueStrategy<Boolean>, KlcPropertyContainer {

	private static final long serialVersionUID = 7989992563410863532L;

	private FloatKlcProperty a;
	private FloatKlcProperty b;

	public FloatLessThan() {
		a = new FloatKlcProperty("A", "A Value. A < B", true, 1f);
		b = new FloatKlcProperty("B", "B Value. A < B", true, 1f);
	}

	@Override
	public Boolean getValue() {
		return a.getValue() < b.getValue();
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[] { a, b };
	}

}