package org.enoy.klc.common.layers;

import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeAdd;
import org.enoy.klc.common.effects.lights.scalemodes.DefaultScaleMode;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleMode;
import org.enoy.klc.common.properties.KlcProperty;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcReadOnlyProperty;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.BooleanKlcProperty;
import org.enoy.klc.common.properties.primitive.BooleanKlcReadOnlyProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcReadOnlyProperty;
import org.enoy.klc.common.properties.primitive.IntegerKlcProperty;
import org.enoy.klc.common.properties.primitive.IntegerKlcReadOnlyProperty;
import org.enoy.klc.common.properties.primitive.StringKlcProperty;
import org.enoy.klc.common.properties.primitive.StringKlcReadOnlyProperty;

public class ReadOnlyEffectLayerInformation implements KlcPropertyContainer {

	protected StringKlcProperty name;
	protected IntegerKlcProperty x;
	protected IntegerKlcProperty y;
	protected IntegerKlcProperty width;
	protected IntegerKlcProperty height;
	protected FloatKlcProperty scaleX;
	protected FloatKlcProperty scaleY;
	protected FloatKlcProperty opacity;
	protected BooleanKlcProperty active;
	protected KlcProperty<BlendMode> blendMode;
	protected KlcProperty<ScaleMode> scaleMode;

	public ReadOnlyEffectLayerInformation(String name) {
		this.name = new StringKlcProperty("Name", "Name of the Layer", false,
				name);
		this.x = new IntegerKlcProperty("X", "X position on the device", true,
				0);
		this.y = new IntegerKlcProperty("Y", "Y position on the device", true,
				0);

		this.width = new IntegerKlcProperty("Width",
				"Width of the effect layer", true, 1);
		this.height = new IntegerKlcProperty("Height",
				"Height of the effect layer", true, 1);

		this.scaleX = new FloatKlcProperty("Scale X",
				"Width Scale percentual of the width", true, 1f);
		this.scaleY = new FloatKlcProperty("Scale Y",
				"Width Scale percentual of the height", true, 1f);

		this.opacity = new FloatKlcProperty("Opacity",
				"Opacity of the effect layer", true, 1f);
		this.active = new BooleanKlcProperty("Active",
				"If this layer is active and rendered", true, true);

		// TODO: move default blendmode to constructor params and pass it in controller
		this.blendMode = new KlcProperty<>(BlendMode.class, "Blend Mode",
				"The Blend Mode of this layer", false,
				BlendModeAdd.getInstance());

		// TODO: move default scalemode to constructor params and pass it in controller
		this.scaleMode = new KlcProperty<>(ScaleMode.class, "Scale Mode",
				"How the rendered effect should be scaled", false,
				DefaultScaleMode.getInstance());
	}

	public StringKlcReadOnlyProperty getName() {
		return name;
	}

	public IntegerKlcReadOnlyProperty getX() {
		return x;
	}

	public IntegerKlcReadOnlyProperty getY() {
		return y;
	}

	public IntegerKlcReadOnlyProperty getWidth() {
		return width;
	}

	public IntegerKlcReadOnlyProperty getHeight() {
		return height;
	}

	public FloatKlcReadOnlyProperty getOpacity() {
		return opacity;
	}

	public BooleanKlcReadOnlyProperty getActive() {
		return active;
	}

	public FloatKlcReadOnlyProperty getScaleX() {
		return scaleX;
	}

	public FloatKlcReadOnlyProperty getScaleY() {
		return scaleY;
	}
	
	public KlcReadOnlyProperty<ScaleMode> getScaleMode(){
		return scaleMode;
	}
	
	public KlcReadOnlyProperty<BlendMode> getBlendMode(){
		return blendMode;
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		// TODO: add blend- and scalemode
		return new KlcWritableProperty[]{name, active, opacity, x, y, width,
				height};
	}

}
