package org.enoy.klc.control.valuestrategies;

import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.updatables.DependentImpl;
import org.enoy.klc.common.updatables.DirtyUpdatable;

@FactoryGenericType(Float.class)
@Name("CPU Usage")
public class CpuUsage extends DependentImpl implements ValueStrategy<Float>, DirtyUpdatable {

	private static final long serialVersionUID = -3109388978685967802L;
	
	private volatile float value = 0;

	@Override
	public Float getValue() {
		return value;
	}

	public static double getProcessCpuLoad() throws Exception {

		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
		AttributeList list = mbs.getAttributes(name, new String[] { "SystemCpuLoad" });

		if (list.isEmpty())
			return Double.NaN;

		Attribute att = (Attribute) list.get(0);
		Double value = (Double) att.getValue();

		// usually takes a couple of seconds before we get real values
		if (value == -1.0)
			return Double.NaN;
		// returns a percentage value with 1 decimal point precision
		return value;
	}

	@Override
	public void update(double delta) {
		try {
			value = (float) getProcessCpuLoad();
			value = Math.max(0, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
