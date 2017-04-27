package org.enoy.klc.common.device;

import org.enoy.klc.common.effects.lights.DeviceLightMatrix;

public interface Device {

	public DeviceInformation getDeviceInformation();
	public void applyDeviceLightMatrix(DeviceLightMatrix deviceLightMatrix);

}
