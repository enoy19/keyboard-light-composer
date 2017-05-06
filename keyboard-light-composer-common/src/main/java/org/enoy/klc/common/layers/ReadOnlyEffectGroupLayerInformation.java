package org.enoy.klc.common.layers;

import java.io.Serializable;

import org.enoy.klc.common.Activatable;
import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeNormal;
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
import org.enoy.klc.common.updatables.Dependent;

public class ReadOnlyEffectGroupLayerInformation
		implements
			KlcPropertyContainer, Dependent, Serializable {

	private static final long serialVersionUID = 6048593486524829918L;
	
	protected StringKlcProperty name;
	protected FloatKlcProperty opacity;
	protected BooleanKlcProperty active;
	protected KlcProperty<BlendMode> blendMode;
	private Activatable dependency;

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
				new BlendModeNormal());
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

	@Override
	public Activatable getDependency() {
		return dependency;
	}

	@Override
	public void setDependency(Activatable dependency) {
		this.dependency = dependency;
		name.setDependency(dependency);
		opacity.setDependency(dependency);
		blendMode.setDependency(dependency);
	}

}
