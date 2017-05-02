package org.enoy.klc.control.devices;

import org.enoy.klc.common.device.Device;
import org.enoy.klc.common.device.DeviceInformation;
import org.enoy.klc.common.effects.lights.DeviceLightMatrix;
import org.enoy.klc.common.effects.lights.LightRow;

public class DummyDevice implements Device{

	private DeviceInformation deviceInformation;
	
	public DummyDevice() {
		deviceInformation = new DeviceInformation("Dummy Device", 10, 10);
	}
	
	@Override
	public void init() {
		System.out.println("Dummy Device initialized");
	}

	@Override
	public void shutdown() {
		System.out.println("Dummy Device shutdown");
	}

	@Override
	public DeviceInformation getDeviceInformation() {
		return deviceInformation;
	}

	@Override
	public void applyDeviceLightMatrix(DeviceLightMatrix deviceLightMatrix) {
		for (int i = 0; i < deviceLightMatrix.getHeight(); i++) {
			LightRow row = deviceLightMatrix.getLightRow(i);
			for (int j = 0; j < deviceLightMatrix.getWidth(); j++) {
				System.out.print(row.getLight(j)+" ");
			}
			System.out.println();
		}
	}

}
