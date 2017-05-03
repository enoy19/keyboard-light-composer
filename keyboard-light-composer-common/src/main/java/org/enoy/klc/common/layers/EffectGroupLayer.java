package org.enoy.klc.common.layers;

import java.util.ArrayList;
import java.util.List;

import org.enoy.klc.common.effects.lights.DeviceLightMatrix;
import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.properties.KlcWritableProperty;

public class EffectGroupLayer extends LayerBase {

	private EffectGroupLayerInformation effectGroupLayerInformation;
	private List<LayerBase> childEffectLayers;

	public EffectGroupLayer(String name) {
		this.effectGroupLayerInformation = new EffectGroupLayerInformation(
				name);
		this.childEffectLayers = new ArrayList<>();
	}

	public EffectGroupLayerInformation getEffectLayerInformation() {
		return effectGroupLayerInformation;
	}

	@Override
	public void render(DeviceLightMatrix dlm) {
		// TODO: move this logic to controller
		// TODO: implement opacity
		
		DeviceLightMatrix copy = dlm.getCopy();

		for (int i = childEffectLayers.size() - 1; i >= 0; i--) {
			LayerBase childEffectLayer = childEffectLayers.get(i);
			if(childEffectLayer.isActive()){
				childEffectLayer.render(dlm);
			}
		}

		BlendMode blendMode = getEffectLayerInformation().getBlendMode()
				.getValue();

		blendMode.blend(dlm, copy);
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return effectGroupLayerInformation.getProperties();
	}

	@Override
	public void delete() {
		super.delete();
		synchronized (childEffectLayers) {
			for (LayerBase layerBase : childEffectLayers) {
				layerBase.delete();
			}
		}
		effectGroupLayerInformation.delete();
	}
	
	@Override
	public boolean isActive() {
		return effectGroupLayerInformation.getActive().getValue();
	}

	public boolean remove(Object o) {
		return childEffectLayers.remove(o);
	}

	public void addChild(LayerBase layer) {
		childEffectLayers.add(layer);
	}
	
	public void addChild(int index, LayerBase layer){
		childEffectLayers.add(index, layer);
	}
	
	public void clearChildren() {
		childEffectLayers.clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName() + " "
				+ effectGroupLayerInformation.getName().getValue()+" - Children: ");
		
		synchronized (childEffectLayers) {
			for (LayerBase layerBase : childEffectLayers) {
				sb.append(layerBase.toString());
				sb.append(' ');
			}
		}
		
		return sb.toString();
	}

}
