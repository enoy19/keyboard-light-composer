package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.updatables.DependentImpl;
import org.enoy.klc.common.updatables.DirtyUpdatable;

@FactoryGenericType(Float.class)
@Name("Pulser")
public class Pulser extends DependentImpl implements ValueStrategy<Float>, DirtyUpdatable, KlcPropertyContainer {

	private volatile float value = 0;
	private boolean up;
	private FloatKlcProperty rate;

	public Pulser() {
		rate = new FloatKlcProperty("Speed", "Speed of this Pulser", true, 1f);
	}
	
	@Override
	public Float getValue() {
		return value;
	}

	@Override
	public void update(double delta) {
		try {
			float rate = (float) (this.rate.getValue() * delta);
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
	public void delete() {
		KlcPropertyContainer.super.delete();
		DirtyUpdatable.super.delete();
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty<?>[]{rate};
	}

}