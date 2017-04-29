package org.enoy.klc.app.components.tree;

import org.enoy.klc.common.layers.LayerBase;

public class LayerBaseContainer<T extends LayerBase> {

	private T layerBase;

	public LayerBaseContainer(T layerBase) {
		this.layerBase = layerBase;
	}

	public T getLayerBase() {
		return layerBase;
	}

}
