package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.StringKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(Boolean.class)
@Name("String Matches Regex")
public class StringMatches implements ValueStrategy<Boolean>, KlcPropertyContainer {

	private static final long serialVersionUID = 1633121096969609982L;

	private StringKlcProperty a;
	private StringKlcProperty regex;

	public StringMatches() {
		a = new StringKlcProperty("A", "String A. A.matches(Regex)", true, "");
		regex = new StringKlcProperty("Regex", "The Regular Expression. A.matches(Regex)", true, "");
	}

	@Override
	public Boolean getValue() {	return a.getValue().matches(regex.getValue()); }

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[]{a, regex};
	}

}