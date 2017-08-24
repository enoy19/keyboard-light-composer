package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Float.class)
@Name("Divide Float")
public class DivideFloat implements ValueStrategy<Float>, KlcPropertyContainer {

	private static final long serialVersionUID = 531899018394044669L;
	
	private FloatKlcProperty a;
	private FloatKlcProperty b;

	public DivideFloat() {
		a = new FloatKlcProperty("A", "First Value. A / B", true, 1f);
		b = new FloatKlcProperty("B", "Second Value. A / B", true, 1f);
	}

	@Override
	public Float getValue() {
		float b = this.b.getValue();
		if(b == 0)
			return 0f;
		
		return a.getValue() / b;
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[] { a, b };
	}

}