package org.enoy.klc.common.updatables;

import java.util.HashSet;
import java.util.Set;

public class UpdatableRegister {

	private static UpdatableRegister instance;
	private Set<Updatable> updatables = new HashSet<>();

	private UpdatableRegister() {

	}

	public synchronized void registerUpdatable(Updatable updatable) {
		updatables.add(updatable);
	}

	public synchronized void unregisterUpdatable(Updatable updatable) {
		updatables.add(updatable);
	}

	public Set<Updatable> getUpdatables() {
		return updatables;
	}

	public static UpdatableRegister getInstance(){
		return instance == null ? instance = new UpdatableRegister() : instance;
	}

}
