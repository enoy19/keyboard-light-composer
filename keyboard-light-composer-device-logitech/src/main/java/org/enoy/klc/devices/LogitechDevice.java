package org.enoy.klc.devices;

import org.enoy.klc.common.device.Device;
import org.enoy.klc.common.device.DeviceInformation;
import org.enoy.klc.common.effects.lights.DeviceLightMatrix;
import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.effects.lights.LightRow;

public class LogitechDevice implements Device {

	private static final int DEVICE_WIDTH = 21;
	private static final int DEVICE_HEIGHT = 6;

	private DeviceInformation deviceInformation;

	public LogitechDevice() {
		deviceInformation = new DeviceInformation("Logitech Device",
				DEVICE_WIDTH, DEVICE_HEIGHT);
	}

	@Override
	public DeviceInformation getDeviceInformation() {
		return deviceInformation;
	}

	@Override
	public void applyDeviceLightMatrix(DeviceLightMatrix deviceLightMatrix) {

		byte[] bitmap = new byte[DEVICE_WIDTH * DEVICE_HEIGHT * 4];

		for (int i = 0; i < DEVICE_HEIGHT; i++) {
			LightRow row = deviceLightMatrix.getLightRow(i);
			for (int j = 0; j < DEVICE_WIDTH; j++) {
				int pos = i * DEVICE_WIDTH + j;
				Light light = row.getLight(j);
				bitmap[pos * 4 + 3] = light.getAlphaByte();
				bitmap[pos * 4 + 2] = light.getRedByte();
				bitmap[pos * 4 + 1] = light.getGreenByte();
				bitmap[pos * 4 + 0] = light.getBlueByte();
			}
		}

		int counter = 0;
		int lightCounter = 0;
		for (byte b : bitmap) {
			counter++;
			
			System.out.print(b+" ");
			if (counter % 4 == 0) {
				System.out.println();
				lightCounter++;
			}
		}
		System.out.println("LIGHTS: "+lightCounter);

		// LogiLED.LogiLedSetLightingFromBitmap(bitmap);

	}

}
