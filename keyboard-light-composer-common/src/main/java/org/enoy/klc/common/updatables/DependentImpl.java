package org.enoy.klc.common.updatables;

import org.enoy.klc.common.Activatable;

public class DependentImpl implements Dependent {

	private Activatable dependency;
	
	@Override
	public Activatable getDependency() {
		return dependency;
	}

	@Override
	public void setDependency(Activatable activatable) {
		this.dependency = activatable;
	}

}
