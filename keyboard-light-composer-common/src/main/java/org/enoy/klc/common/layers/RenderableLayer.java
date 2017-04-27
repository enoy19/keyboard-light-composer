package org.enoy.klc.common.layers;

import org.enoy.klc.common.effects.lights.DeviceLightMatrix;

public interface RenderableLayer {

	public void render(DeviceLightMatrix dlm);
	
}
