package org.enoy.klc.common.effects;

import java.util.List;

import org.enoy.klc.common.device.Device;
import org.enoy.klc.common.layers.RenderableLayer;

public interface LayerRenderer {

	public void setDevice(Device device);
	public void render(List<? extends RenderableLayer> renderable);

}
