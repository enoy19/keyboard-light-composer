package org.enoy.klc.app.components.utils;

import org.enoy.klc.app.components.tree.LayerBaseContainer;
import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.LayerBase;

import javafx.scene.control.TreeItem;

public class LayerTreeItemListenerUtil {
	
	public static void addListenersToTreeItem(TreeItem<LayerBaseContainer<?>> treeItem) {

		treeItem.parentProperty().addListener((v, o, n) -> {
			LayerBaseContainer<?> oldValue = o != null ? o.getValue() : null;
			LayerBaseContainer<?> newValue = n != null ? n.getValue() : null;
			LayerBase value = treeItem.getValue().getLayerBase();
			if (oldValue != null) {
				EffectGroupLayer parentGroup = (EffectGroupLayer) oldValue.getLayerBase();
				parentGroup.remove(value);
			}

			if (newValue != null) {
				EffectGroupLayer parentGroup = (EffectGroupLayer) newValue.getLayerBase();
				parentGroup.addChild(value);

				// reorganize
				parentGroup.clearChildren();
				n.getChildren().forEach(ti -> {
					parentGroup.addChild(ti.getValue().getLayerBase());
				});
			}
		});

	}


}
