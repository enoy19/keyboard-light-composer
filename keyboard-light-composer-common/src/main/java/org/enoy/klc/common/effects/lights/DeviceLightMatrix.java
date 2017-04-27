package org.enoy.klc.common.effects.lights;

public class DeviceLightMatrix extends LightMatrix{

	public DeviceLightMatrix(int width, int height) {
		super(width, height);
	}
	
	@Override
	public DeviceLightMatrix getCopy() {
		DeviceLightMatrix copy = new DeviceLightMatrix(getWidth(), getHeight());
		setLightMatrix(copy);
		return copy;
	}

}
