package org.enoy.klc.effects.standard;

import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.effects.lights.LightMatrix;
import org.enoy.klc.common.factories.Group;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcProperty;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;

import javafx.scene.paint.Color;

@Name("Solid Color")
@Group("Standard Effects")
public class SolidColorEffect implements Effect, KlcPropertyContainer {

	private KlcProperty<Color> color;

	public SolidColorEffect() {
		this.color = new KlcProperty<Color>(Color.class, "Color",
				"The color of this effect", true, new Color(0, 1, 0, 1));
	}

	@Override
	public LightMatrix getColorMatrix() {
		Color colorValue = color.getValue();
		LightMatrix lightMatrix = new LightMatrix(1, 1);
		lightMatrix.setLight(0, 0, colorValue.getRed(), colorValue.getGreen(),
				colorValue.getBlue(), colorValue.getOpacity());
		return lightMatrix;
	}

	@Override
	public String getName() {
		return "Solid Color";
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty[]{color};
	}

}
