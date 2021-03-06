package org.enoy.klc.app.components.tree;

import javafx.scene.control.TreeItem;
import org.enoy.klc.common.layers.LayerBase;

public class LayerTreeCellDragboard {

	private static TreeItem<LayerBaseContainer<? extends LayerBase>> currentlyDraggedLayerTreeItem;

	public static TreeItem<LayerBaseContainer<? extends LayerBase>> getCurrentlyDraggedLayerTreeItem() {
		return currentlyDraggedLayerTreeItem;
	}

	public static void setCurrentlyDraggedLayerTreeItem(
			TreeItem<LayerBaseContainer<? extends LayerBase>> currentlyDraggedLayerTreeCell) {
		LayerTreeCellDragboard.currentlyDraggedLayerTreeItem = currentlyDraggedLayerTreeCell;
	}

	public static boolean isLayerDragged(TreeItem<?> layerTreeCell) {
		return layerTreeCell.equals(currentlyDraggedLayerTreeItem);
	}

}
