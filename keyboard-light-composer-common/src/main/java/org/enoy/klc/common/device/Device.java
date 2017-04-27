package org.enoy.klc.common.device;

import org.enoy.klc.common.effects.lights.DeviceLightMatrix;

public interface Device {

	public void init();
	public void shutdown();
	public DeviceInformation getDeviceInformation();
	public void applyDeviceLightMatrix(DeviceLightMatrix deviceLightMatrix);

}
