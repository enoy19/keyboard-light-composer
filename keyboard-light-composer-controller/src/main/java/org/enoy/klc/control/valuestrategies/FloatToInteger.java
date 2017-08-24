package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Integer.class)
@Name("Float to Integer")
public class FloatToInteger implements ValueStrategy<Integer>, KlcPropertyContainer {

	private static final long serialVersionUID = 8112598619441575620L;

	private FloatKlcProperty a;

	public FloatToInteger() {
		a = new FloatKlcProperty("A", "The float value that will be converted into an integer", true, 1f);
	}

	@Override
	public Integer getValue() {
		return a.getValue().intValue();
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[]{a};
	}

}