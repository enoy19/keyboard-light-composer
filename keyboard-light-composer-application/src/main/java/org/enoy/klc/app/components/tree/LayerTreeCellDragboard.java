package org.enoy.klc.app.components.tree;

import org.enoy.klc.common.layers.LayerBase;

import javafx.scene.control.TreeCell;

public class LayerTreeCellDragboard {

	private static TreeCell<LayerBaseContainer<? extends LayerBase>> currentlyDraggedLayerTreeCell;

	public static TreeCell<LayerBaseContainer<? extends LayerBase>> getCurrentlyDraggedLayerTreeCell() {
		return currentlyDraggedLayerTreeCell;
	}

	public static void setCurrentlyDraggedLayerTreeCell(
			TreeCell<LayerBaseContainer<? extends LayerBase>> currentlyDraggedLayerTreeCell) {
		LayerTreeCellDragboard.currentlyDraggedLayerTreeCell = currentlyDraggedLayerTreeCell;
	}

	public static boolean isLayerDragged(TreeCell<?> layerTreeCell) {
		return layerTreeCell.equals(currentlyDraggedLayerTreeCell);
	}

}
