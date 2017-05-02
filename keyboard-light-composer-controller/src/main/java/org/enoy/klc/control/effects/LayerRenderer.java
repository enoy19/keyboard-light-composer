package org.enoy.klc.control.effects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.enoy.klc.common.device.Device;
import org.enoy.klc.common.layers.RenderableLayer;
import org.enoy.klc.control.StopPauseLoop;

public abstract class LayerRenderer extends StopPauseLoop {

	protected Device device;
	private List<RenderableLayer> renderables;

	public LayerRenderer() {
		renderables = new ArrayList<>();
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	public abstract void render();

	@Override
	public void executeLoop(double delta) {
		render();
	}

	public void setRenderables(Collection<? extends RenderableLayer> renderables){
		this.renderables.clear();
		this.renderables.addAll(renderables);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends RenderableLayer> List<T> getRenderables() {
		return (List<T>) renderables;
	}

}
