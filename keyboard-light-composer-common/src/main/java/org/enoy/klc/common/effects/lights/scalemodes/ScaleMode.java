package org.enoy.klc.common.effects.lights.scalemodes;

import org.enoy.klc.common.effects.lights.LightMatrix;

public interface ScaleMode {

	public LightMatrix scale(LightMatrix lightMatrix, int targetWidth, int targetHeight);
	
}
