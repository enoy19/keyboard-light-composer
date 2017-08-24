package org.enoy.klc.effects.standard;

import javafx.scene.paint.Color;
import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.effects.lights.LightMatrix;
import org.enoy.klc.common.factories.Group;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.model.KlcColor;
import org.enoy.klc.common.properties.KlcProperty;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;

@Name("Solid Color")
@Group("Standard Effects")
public class SolidColorEffect implements Effect, KlcPropertyContainer {

	private static final long serialVersionUID = 4912455499181262839L;
	private KlcProperty<KlcColor> color;

	public SolidColorEffect() {
		this.color = new KlcProperty<KlcColor>(KlcColor.class, "Color",
				"The color of this effect", true, new KlcColor());
	}

	@Override
	public LightMatrix getColorMatrix() {
		KlcColor klcColorValue = color.getValue();
		Color colorValue = klcColorValue.getColor(); 
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
