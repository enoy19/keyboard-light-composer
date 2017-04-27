package org.enoy.klc.common.layers;

import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.properties.KlcProperty;
import org.enoy.klc.common.properties.primitive.BooleanKlcProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.primitive.StringKlcProperty;

public class EffectGroupLayerInformation
		extends
			ReadOnlyEffectGroupLayerInformation {

	public EffectGroupLayerInformation(String name) {
		super(name);
	}

	@Override
	public BooleanKlcProperty getActive() {
		return active;
	}
	
	@Override
	public StringKlcProperty getName() {
		return name;
	}
	
	@Override
	public FloatKlcProperty getOpacity() {
		return opacity;
	}
	
	@Override
	public KlcProperty<BlendMode> getBlendMode() {
		return blendMode;
	}
	
}
