package org.enoy.klc.control.updater;

import org.enoy.klc.common.Activatable;
import org.enoy.klc.common.updatables.Dependent;
import org.enoy.klc.common.updatables.UpdatableRegister;
import org.enoy.klc.control.StopPauseLoop;

public class Updater extends StopPauseLoop {

	private UpdatableRegister updatableRegister;

	public void setUpdatableRegister(UpdatableRegister updatableRegister) {
		this.updatableRegister = updatableRegister;
	}

	@Override
	public void executeLoop(double delta) {
		synchronized (updatableRegister) {
			updatableRegister.getRegisteredParallelStream()
					.filter(u -> u.isDirty())
					.filter(u->{
						if(u instanceof Dependent){
							Activatable dependency = ((Dependent) u).getDependency();
							return dependency == null || dependency.isActive();
						}
						return true;
					})
					.forEach(u -> u.updateAndClean(delta));
		}
	}

}
