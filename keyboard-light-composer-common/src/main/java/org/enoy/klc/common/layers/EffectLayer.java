package org.enoy.klc.common.layers;

import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.effects.lights.DeviceLightMatrix;
import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.effects.lights.LightMatrix;
import org.enoy.klc.common.effects.lights.LightRow;
import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleMode;
import org.enoy.klc.common.properties.KlcWritableProperty;

public class EffectLayer extends LayerBase {

	private EffectLayerInformation effectLayerInformation;
	private Effect effect;

	public EffectLayer(Effect effect) {
		this.effect = effect;
		this.effectLayerInformation = new EffectLayerInformation(
				effect.getName());
	}

	public Effect getEffect() {
		return effect;
	}

	public EffectLayerInformation getEffectLayerInformation() {
		return effectLayerInformation;
	}

	@Override
	public boolean isActive() {
		return this.effectLayerInformation.active.getValue();
	}

	@Override
	public void render(DeviceLightMatrix dlm) {
		// TODO: move this logic to controller

		int x = effectLayerInformation.getX().getValue();
		int y = effectLayerInformation.getY().getValue();
		int width = effectLayerInformation.getWidth().getValue();
		int height = effectLayerInformation.getHeight().getValue();
		float scaleX = effectLayerInformation.getScaleX().getValue();
		float scaleY = effectLayerInformation.getScaleY().getValue();
		ScaleMode scaleMode = effectLayerInformation.getScaleMode().getValue();
		BlendMode blendMode = effectLayerInformation.getBlendMode().getValue();

		width = (int) ((float) width * scaleX);
		height = (int) ((float) height * scaleY);

		LightMatrix colorMatrix = effect.getColorMatrix();
		LightMatrix scaledMatrix = scaleMode.scale(colorMatrix, width, height);

		int dlmBottom = Math.min(dlm.getHeight(), y + height);
		int dlmRight = Math.min(dlm.getWidth(), x + width);
		int dlmTop = Math.max(0, y);
		int dlmLeft = Math.max(0, x);

		for (int i = dlmTop; i < dlmBottom; i++) {
			LightRow lightRow = dlm.getLightRow(i);
			for (int j = dlmLeft; j < dlmRight; j++) {
				Light originalLight = lightRow.getLight(j);
				
				int effectX = j - x;
				int effectY = i - y;
				
				Light effectLight = scaledMatrix.getLight(effectY, effectX);
				Light blendedLight = blendMode.blend(originalLight, effectLight);
				
				originalLight.set(blendedLight);
			}
		}

	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return effectLayerInformation.getProperties();
	}

}
