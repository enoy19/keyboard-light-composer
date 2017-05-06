package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.model.KlcColor;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

@FactoryGenericType(KlcColor.class)
@Name("Parameterized Color")
public class ColorValueStrategy implements ValueStrategy<KlcColor>, KlcPropertyContainer {

	private static final long serialVersionUID = 7575700020725614963L;
	
	private FloatKlcProperty red;
	private FloatKlcProperty green;
	private FloatKlcProperty blue;
	private FloatKlcProperty alpha;

	public ColorValueStrategy() {
		red = new FloatKlcProperty("Red", "Red value (0-1)", true, 0f);
		green = new FloatKlcProperty("Green", "Green value (0-1)", true, 0f);
		blue = new FloatKlcProperty("Blue", "Blue value (0-1)", true, 0f);
		alpha = new FloatKlcProperty("Alpha", "Alpha value (0-1)", true, 1f);
	}

	private KlcColor getKlcColor() {
		return new KlcColor(red.getValue(), green.getValue(), blue.getValue(), alpha.getValue());
	}

	@Override
	public KlcColor getValue() {
		return getKlcColor();
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[] {red, green, blue, alpha};
	}

}
