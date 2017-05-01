package org.enoy.klc.control.valuestrategies;

import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategyName;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategyType;
import org.enoy.klc.common.updatables.DirtyUpdatable;

@ValueStrategyType(Float.class)
@ValueStrategyName("CPU Usage")
public class CpuUsage implements ValueStrategy<Float>, DirtyUpdatable {

	private float value = 0;
	
	@Override
	public Float getValue() {
		return value;
	}

	@Override
	public void update(double delta) {
		try {
			value = (float) getProcessCpuLoad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static double getProcessCpuLoad() throws Exception {

	    MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();

	    // usually takes a couple of seconds before we get real values
	    if (value == -1.0)      return Double.NaN;
	    // returns a percentage value with 1 decimal point precision
	    return ((int)(value * 1000) / 10.0);
	}

}
