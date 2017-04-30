package org.enoy.klc.app.components.tree;

import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;

import javafx.scene.control.TreeCell;
import javafx.scene.input.DragEvent;

public class LayerBaseTreeCell
		extends
			TreeCell<LayerBaseContainer<? extends LayerBase>> {

	public LayerBaseTreeCell() {

		setOnDragOver(this::onDragOver);

	}

	@Override
	protected void updateItem(LayerBaseContainer<? extends LayerBase> item,
			boolean empty) {

		if (!empty && item != null) {
			LayerBase layerBase = item.getLayerBase();

			String name = "-";

			if (layerBase instanceof EffectLayer) {
				EffectLayer effectLayer = (EffectLayer) layerBase;
				name = effectLayer.getEffectLayerInformation().getName()
						.getValue();
			} else if (layerBase instanceof EffectGroupLayer) {
				EffectGroupLayer effectGroupLayer = (EffectGroupLayer) layerBase;
				name = effectGroupLayer.getEffectLayerInformation().getName()
						.getValue();
			}

			setText(name);

		} else {
			setText(null);
		}

	}
	
	private void onDragOver(DragEvent event){
		
	}

}
