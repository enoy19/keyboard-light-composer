package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.updatables.DependentImpl;

@FactoryGenericType(Float.class)
@Name("Multiply Float")
public class MultiplyFloat extends DependentImpl implements ValueStrategy<Float>, KlcPropertyContainer {

	private static final long serialVersionUID = -9191133758532058858L;
	
	private FloatKlcProperty a;
	private FloatKlcProperty b;

	public MultiplyFloat() {
		a = new FloatKlcProperty("A", "First Value. A * B", true, 1f);
		b = new FloatKlcProperty("B", "Second Value. A * B", true, 1f);
	}

	@Override
	public Float getValue() {
		return a.getValue() * b.getValue();
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[] { a, b };
	}

}