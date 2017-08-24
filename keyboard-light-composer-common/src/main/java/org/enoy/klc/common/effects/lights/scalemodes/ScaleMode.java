package org.enoy.klc.common.effects.lights.scalemodes;

import org.enoy.klc.common.effects.lights.LightMatrix;

import java.io.Serializable;

public interface ScaleMode extends Serializable{

	public LightMatrix scale(LightMatrix lightMatrix, int targetWidth, int targetHeight);
	
}
