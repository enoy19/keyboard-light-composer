package org.enoy.klc.common.layers;

import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleMode;
import org.enoy.klc.common.properties.KlcProperty;
import org.enoy.klc.common.properties.primitive.BooleanKlcProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.primitive.IntegerKlcProperty;
import org.enoy.klc.common.properties.primitive.StringKlcProperty;

public class EffectLayerInformation extends ReadOnlyEffectLayerInformation {

	public EffectLayerInformation(String name) {
		super(name);
	}

	@Override
	public StringKlcProperty getName() {
		return name;
	}
	
	@Override
	public IntegerKlcProperty getHeight() {
		return height;
	}
	
	@Override
	public FloatKlcProperty getOpacity() {
		return opacity;
	}

	@Override
	public IntegerKlcProperty getWidth() {
		return width;
	}
	
	@Override
	public IntegerKlcProperty getX() {
		return x;
	}
	
	@Override
	public IntegerKlcProperty getY() {
		return y;
	}
	
	@Override
	public BooleanKlcProperty getActive() {
		return active;
	}
	
	@Override
	public FloatKlcProperty getScaleX() {
		return scaleX;
	}
	
	@Override
	public FloatKlcProperty getScaleY() {
		return scaleY;
	}
	
	@Override
	public KlcProperty<ScaleMode> getScaleMode() {
		return scaleMode;
	}
	
	@Override
	public KlcProperty<BlendMode> getBlendMode() {
		return blendMode;
	}
	
}
