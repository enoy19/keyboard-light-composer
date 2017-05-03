package org.enoy.klc.common.updatables;

import org.enoy.klc.common.Activatable;

public interface Dependent {
	
	public Activatable getDependency();
	public void setDependency(Activatable dependency);

}
