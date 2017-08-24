package org.enoy.klc.control;

import org.enoy.klc.common.Activatable;
import org.enoy.klc.common.device.DeviceInformation;
import org.enoy.klc.common.effects.lights.DeviceLightMatrix;
import org.enoy.klc.common.layers.RenderableLayer;
import org.enoy.klc.control.effects.LayerRenderer;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultRenderer extends LayerRenderer {

	@Override
	public void render() {
		if (device != null) {
			DeviceInformation deviceInformation = device.getDeviceInformation();
			DeviceLightMatrix dlm = new DeviceLightMatrix(
					deviceInformation.getWidth(),
					deviceInformation.getHeight());

			List<? extends RenderableLayer> result = //
					getRenderables().parallelStream().filter(r -> {
						if (r instanceof Activatable) {
							return ((Activatable) r).isActive();
						}
						return true;
					}).collect(Collectors.toList());

			for (int i = result.size() - 1; i >= 0; i--) {
				result.get(i).render(dlm);
			}

			device.applyDeviceLightMatrix(dlm);
		}
	}

	@Override
	public void reset() {
		
	}

}
