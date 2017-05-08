package org.enoy.klc.effects.standard;

import org.enoy.klc.common.Resettable;
import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.effects.lights.LightMatrix;
import org.enoy.klc.common.factories.Group;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.model.KlcColor;
import org.enoy.klc.common.properties.KlcProperty;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.updatables.DependentImpl;
import org.enoy.klc.common.updatables.DirtyUpdatable;

@Name("Horizontal Line")
@Group("Standard Effects")
public class HorizontalLineEffect extends DependentImpl implements Effect, KlcPropertyContainer, DirtyUpdatable, Resettable {

	private static final long serialVersionUID = 9160815839275855707L;

	private static final int WIDTH = 25;
	private static final int HEIGHT = 10;

	private KlcProperty<KlcColor> color;
	private FloatKlcProperty speed;
	private FloatKlcProperty start;

	private volatile float value;
	private volatile boolean up;

	public HorizontalLineEffect() {
		this.color = new KlcProperty<KlcColor>(KlcColor.class, "Color", "The color of the line", true, new KlcColor());

		this.speed = new FloatKlcProperty("Speed", "Movement speed of the line", true, 1f);
		this.start = new FloatKlcProperty("Start at", "Start position of the Line", true, 0f);
	}

	@Override
	public LightMatrix getColorMatrix() {
		KlcColor klcColorValue = color.getValue();
		LightMatrix lightMatrix = new LightMatrix(WIDTH, HEIGHT);

		int x = (int) (value * WIDTH);
		x = x >= WIDTH ? WIDTH - 1 : x < 0 ? 0 : x;
		for (int i = 0; i < HEIGHT; i++) {
			lightMatrix.setLight(i, x, new Light(klcColorValue));
		}

		return lightMatrix;
	}

	@Override
	public String getName() {
		return "Horizontal Line";
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty[] { color, speed, start };
	}

	@Override
	public void delete() {
		KlcPropertyContainer.super.delete();
		DirtyUpdatable.super.delete();
	}

	@Override
	public void update(double delta) {
		// TODO: pass layerinformation? get layerinformation from Effect
		// interface?

		try {
			float rate = (float) (this.speed.getValue() * delta);
			rate = up ? rate : -rate;
			value += rate;
			if (value <= 0) {
				value = 0;
				up = true;
			} else if (value >= 1) {
				value = 1;
				up = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reset() {
		value = start.getValue();
	}

}
