package org.enoy.klc.control.updater;

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
					.forEach(u -> u.updateAndClean(delta));
		}
	}

}
