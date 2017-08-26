package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.BooleanKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Boolean.class)
@Name("Not")
public class Not implements ValueStrategy<Boolean>, KlcPropertyContainer {

	private static final long serialVersionUID = 4773886218027084490L;

	private BooleanKlcProperty a;

	public Not() {
		a = new BooleanKlcProperty("Value", "Value to be inverted. !VALUE", true, false);
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty[]{ a };
	}

	@Override
	public Boolean getValue() {
		return null;
	}

}
