package org.enoy.klc.app.components.utils;

import javafx.scene.control.TreeItem;
import org.enoy.klc.app.components.tree.LayerBaseContainer;
import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.LayerBase;

import java.util.List;

public class LayerTreeItemUtil {

	public static TreeItem<LayerBaseContainer<?>> getTreeItem(LayerBase root) {
		TreeItem<LayerBaseContainer<? extends LayerBase>> rootTreeItem = createTreeItem(root);
		addChildren(root, rootTreeItem);
		return rootTreeItem;

	}

	private static void addChildren(LayerBase root, TreeItem<LayerBaseContainer<?>> rootTreeItem) {
		if (root instanceof EffectGroupLayer) {
			EffectGroupLayer effectGroupLayer = (EffectGroupLayer) root;
			List<LayerBase> children = effectGroupLayer.getChildren();
			for (LayerBase child : children) {
				TreeItem<LayerBaseContainer<?>> childTreeItem = getTreeItem(rootTreeItem, child);
				rootTreeItem.getChildren().add(childTreeItem);
			}

		}
	}

	private static TreeItem<LayerBaseContainer<?>> getTreeItem(
			TreeItem<LayerBaseContainer<? extends LayerBase>> rootTreeItem, LayerBase layerBase) {
		TreeItem<LayerBaseContainer<?>> treeItem = createTreeItem(layerBase);
		addChildren(layerBase, treeItem);
		return treeItem;
	}

	private static TreeItem<LayerBaseContainer<?>> createTreeItem(LayerBase layerBase) {

		LayerBaseContainer<?> layerBaseContainer = new LayerBaseContainer<>(layerBase);

		TreeItem<LayerBaseContainer<?>> treeItem = new TreeItem<>(layerBaseContainer);
		LayerTreeItemListenerUtil.addListenersToTreeItem(treeItem);

		return treeItem;

	}

}
