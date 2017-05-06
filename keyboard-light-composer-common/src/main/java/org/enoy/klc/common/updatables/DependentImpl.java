package org.enoy.klc.common.updatables;

import org.enoy.klc.common.Activatable;

public class DependentImpl implements Dependent {

	private static final long serialVersionUID = 1036106766505723763L;
	
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
