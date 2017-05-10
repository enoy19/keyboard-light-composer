package org.enoy.klc.control.valuestrategies;

import java.awt.MouseInfo;
import java.awt.Point;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.updatables.DependentImpl;
import org.enoy.klc.common.updatables.DirtyUpdatable;

@FactoryGenericType(Float.class)
@Name("Mouse Speed")
public class MouseSpeed extends DependentImpl implements ValueStrategy<Float>, DirtyUpdatable {

	private static final long serialVersionUID = -3453853595576795736L;
	
	private volatile int x = 0;
	private volatile int y = 0;
	private volatile float value;

	@Override
	public Float getValue() {
		return value;
	}

	@Override
	public void update(double delta) {
		Point info = MouseInfo.getPointerInfo().getLocation();


		int diffX = Math.abs(info.x - x);
		int diffY = Math.abs(info.y - y);
		
		x = info.x;
		y = info.y;
		
		value = ( (diffX + diffY) / 2f ) / 100f;
		
	}

}
