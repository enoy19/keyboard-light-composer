package org.enoy.klc.common.updatables;

import org.enoy.klc.common.Activatable;

import java.io.Serializable;

public interface Dependent extends Serializable {
	
	public Activatable getDependency();
	public void setDependency(Activatable dependency);

}
