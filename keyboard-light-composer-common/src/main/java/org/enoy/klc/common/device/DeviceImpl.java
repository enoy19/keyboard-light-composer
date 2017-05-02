package org.enoy.klc.common.device;

public abstract class DeviceImpl implements Device {

	private boolean initialized;
	
	@Override
	public synchronized void init() {
		if(!initialized){
			initialized = true;
			internalInit();
		}
	}

	@Override
	public synchronized void shutdown() {
		if(initialized){
			internalShutdown();
		}
	}
	
	protected abstract void internalInit();
	protected abstract void internalShutdown();
	
	@Override
	public String toString() {
		return getName();
	}

}
