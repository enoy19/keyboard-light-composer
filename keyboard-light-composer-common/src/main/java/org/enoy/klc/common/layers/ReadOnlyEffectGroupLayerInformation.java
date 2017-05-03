package org.enoy.klc.common.layers;

import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeAdd;
import org.enoy.klc.common.properties.KlcProperty;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcReadOnlyProperty;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.BooleanKlcProperty;
import org.enoy.klc.common.properties.primitive.BooleanKlcReadOnlyProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcReadOnlyProperty;
import org.enoy.klc.common.properties.primitive.StringKlcProperty;
import org.enoy.klc.common.properties.primitive.StringKlcReadOnlyProperty;

public class ReadOnlyEffectGroupLayerInformation
		implements
			KlcPropertyContainer {

	protected StringKlcProperty name;
	protected FloatKlcProperty opacity;
	protected BooleanKlcProperty active;
	protected KlcProperty<BlendMode> blendMode;

	public ReadOnlyEffectGroupLayerInformation(String name) {
		this.name = new StringKlcProperty("Name", "Name of the Layer", false,
				name);
		this.opacity = new FloatKlcProperty("Opacity",
				"Opacity of the effect layer", true, 1f);
		this.active = new BooleanKlcProperty("Active",
				"If this layer is active and rendered", true, true);

		// TODO: pass default blendmode in constructor and instantiate in controller
		this.blendMode = new KlcProperty<BlendMode>(BlendMode.class,
				"Blend Mode", "The Blend Mode of this layer", false,
				new BlendModeAdd());
	}

	public StringKlcReadOnlyProperty getName() {
		return name;
	}

	public FloatKlcReadOnlyProperty getOpacity() {
		return opacity;
	}

	public BooleanKlcReadOnlyProperty getActive() {
		return active;
	}

	public KlcReadOnlyProperty<BlendMode> getBlendMode() {
		return blendMode;
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty[]{name, active, opacity, blendMode};
	}

}
