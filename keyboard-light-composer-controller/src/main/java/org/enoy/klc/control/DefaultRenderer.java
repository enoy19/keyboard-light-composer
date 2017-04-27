package org.enoy.klc.control;

import java.util.List;
import java.util.stream.Collectors;

import org.enoy.klc.common.Activatable;
import org.enoy.klc.common.device.Device;
import org.enoy.klc.common.device.DeviceInformation;
import org.enoy.klc.common.effects.LayerRenderer;
import org.enoy.klc.common.effects.lights.DeviceLightMatrix;
import org.enoy.klc.common.layers.RenderableLayer;

public class DefaultRenderer implements LayerRenderer {

	private Device device;

	@Override
	public void setDevice(Device device) {
		this.device = device;
	}

	@Override
	public void render(List<? extends RenderableLayer> renderable) {
		DeviceInformation deviceInformation = device.getDeviceInformation();
		DeviceLightMatrix dlm = new DeviceLightMatrix(
				deviceInformation.getWidth(), deviceInformation.getHeight());

		List<? extends RenderableLayer> result = //
				renderable.parallelStream().filter(r -> {
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
