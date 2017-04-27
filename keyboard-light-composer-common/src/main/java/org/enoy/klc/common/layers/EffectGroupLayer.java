package org.enoy.klc.common.layers;

import java.util.ArrayList;
import java.util.List;

import org.enoy.klc.common.effects.lights.DeviceLightMatrix;
import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.properties.KlcWritableProperty;

public class EffectGroupLayer extends LayerBase {

	private EffectGroupLayerInformation effectGroupLayerInformation;
	private List<? extends LayerBase> childEffectLayers;

	public EffectGroupLayer() {
		this.childEffectLayers = new ArrayList<>();
	}

	public EffectGroupLayer(String name) {
		this.effectGroupLayerInformation = new EffectGroupLayerInformation(
				name);
	}

	public EffectGroupLayerInformation getEffectLayerInformation() {
		return effectGroupLayerInformation;
	}

	@Override
	public void render(DeviceLightMatrix dlm) {
		// TODO: move this logic to controller
		DeviceLightMatrix copy = dlm.getCopy();

		for (int i = childEffectLayers.size() - 1; i <= 0; i--) {
			childEffectLayers.get(i).render(dlm);
		}
		
		BlendMode blendMode = getEffectLayerInformation().getBlendMode().getValue();
		
		blendMode.blend(dlm, copy);
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return effectGroupLayerInformation.getProperties();
	}

	@Override
	public boolean isActive() {
		return effectGroupLayerInformation.getActive().getValue();
	}

}
