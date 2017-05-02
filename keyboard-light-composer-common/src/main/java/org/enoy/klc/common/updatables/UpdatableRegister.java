package org.enoy.klc.common.updatables;

import org.enoy.klc.common.Register;

public class UpdatableRegister extends Register<Updatable>{

	private static UpdatableRegister instance;

	private UpdatableRegister() {

	}

	@Override
	public synchronized void register(Updatable object) {
		super.register(object);
	}
	
	@Override
	public synchronized void unregister(Updatable object) {
		super.unregister(object);
	}
	
	public static UpdatableRegister getInstance(){
		return instance == null ? instance = new UpdatableRegister() : instance;
	}

}
