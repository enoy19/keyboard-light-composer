package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.StringKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Boolean.class)
@Name("String Equals")
public class StringEquals implements ValueStrategy<Boolean>, KlcPropertyContainer {

	private static final long serialVersionUID = -3363522822969420487L;

	private StringKlcProperty a;
	private StringKlcProperty b;

	public StringEquals() {
		a = new StringKlcProperty("A", "String A. A.equals(B)", true, "");
		b = new StringKlcProperty("B", "String B. A.equals(B)", true, "");
	}

	@Override
	public Boolean getValue() {
		return a.getValue().equals(b.getValue());
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[] { a, b };
	}

}