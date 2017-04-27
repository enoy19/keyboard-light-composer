package org.enoy.klc.control.updater;

import org.enoy.klc.common.updatables.UpdatableRegister;

public class Updater implements Runnable {

	private UpdatableRegister updatableRegister;
	private boolean stopped = false;
	private boolean paused = false;

	@Override
	public void run() {
		long time = System.currentTimeMillis();

		while (!stopped && !Thread.interrupted()) {
			while (!paused && !Thread.interrupted()) {
				long deltaLong = time - System.currentTimeMillis();
				time = System.currentTimeMillis();
				double delta = (double) deltaLong / 1000f;

				synchronized (updatableRegister) {
					updatableRegister.getUpdatables().parallelStream()
							.filter(u -> u.isDirty())
							.forEach(u -> u.updateAndClean(delta));
				}

				sleep(20);
			}
			sleep(50);
		}

	}

	public void setUpdatableRegister(UpdatableRegister updatableRegister) {
		this.updatableRegister = updatableRegister;
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// ignore
		}
	}

}
