package org.enoy.klc.common.effects.lights.scalemodes;

import java.io.Serializable;

import org.enoy.klc.common.effects.lights.LightMatrix;

public interface ScaleMode extends Serializable{

	public LightMatrix scale(LightMatrix lightMatrix, int targetWidth, int targetHeight);
	
}
