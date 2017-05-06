package org.enoy.klc.common.updatables;

import java.io.Serializable;

import org.enoy.klc.common.Activatable;

public interface Dependent extends Serializable {
	
	public Activatable getDependency();
	public void setDependency(Activatable dependency);

}
