package org.enoy.klc.control.updater;

import org.enoy.klc.common.updatables.UpdatableRegister;
import org.enoy.klc.control.StopPauseLoop;

public class Updater extends StopPauseLoop {

	private UpdatableRegister updatableRegister;
	private Runnable onPause;

	public void setUpdatableRegister(UpdatableRegister updatableRegister) {
		this.updatableRegister = updatableRegister;
	}

	public void setOnPause(Runnable onPause) {
		this.onPause = onPause;
	}
	
	@Override
	public void setPaused(boolean paused) {
		super.setPaused(paused);
		if(!paused){
			onPause.run();
		}
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
