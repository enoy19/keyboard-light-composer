package org.enoy.klc.common.device;

import org.enoy.klc.common.Register;

public class DeviceRegister extends Register<Device> {

	private static DeviceRegister instance;

	private DeviceRegister() {

	}

	public static DeviceRegister getInstance() {
		return instance == null ? instance = new DeviceRegister() : instance;
	}
	
	public void shutdown(){
		getRegisteredObjects().forEach(d->d.shutdown());
	}

}
