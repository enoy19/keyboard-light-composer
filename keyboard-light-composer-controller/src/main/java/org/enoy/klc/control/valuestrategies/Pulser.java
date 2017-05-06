package org.enoy.klc.control.valuestrategies;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.enoy.klc.common.Resettable;
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
public class Pulser extends DependentImpl implements ValueStrategy<Float>, DirtyUpdatable, KlcPropertyContainer, Resettable {

	private static final long serialVersionUID = 6267695861865537928L;

	private volatile float value = 0;
	private boolean up;
	private FloatKlcProperty rate;
	private FloatKlcProperty start;

	public Pulser() {
		rate = new FloatKlcProperty("Speed", "Speed of this Pulser", true, 1f);
		start = new FloatKlcProperty("Start at", "The starting point of this pulser", true, 0f);
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
		return new KlcWritableProperty<?>[] { rate, start };
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		if(start == null){
			start = new FloatKlcProperty("Start at", "The starting point of this pulser", true, 0f);
		}
	}

	@Override
	public void reset() {
		value = start.getValue();
		up = true;
	}

}