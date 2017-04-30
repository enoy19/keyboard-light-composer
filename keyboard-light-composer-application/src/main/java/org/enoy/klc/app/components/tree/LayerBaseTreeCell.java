package org.enoy.klc.app.components.tree;

import java.util.HashMap;
import java.util.Map;

import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.control.utils.DelayedExecuter;
import org.enoy.klc.control.utils.LayerBaseUtil;
import org.enoy.klc.control.utils.ListItemSwapUtil;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class LayerBaseTreeCell
		extends
			TreeCell<LayerBaseContainer<? extends LayerBase>> {

	private static final String DRAG_BELOW = "drag-below-line";
	private static final String DRAG_ABOVE = "drag-above-line";
	private static final String DRAG_INSIDE = "drag-inside";
	private static final DataFormat LAYER_DATA_FORMAT = new DataFormat(
			LayerBase.class.getName());

	public LayerBaseTreeCell() {

		setOnDragOver(this::onDragOver);
		setOnDragDetected(this::onDragDetected);
		setOnDragExited(this::onDragExited);
		setOnDragDropped(this::onDragDropped);
		setOnDragDone(this::onDragDone);

		getStyleClass().add("fa");
	}

	@Override
	protected void updateItem(LayerBaseContainer<? extends LayerBase> item,
			boolean empty) {
		super.updateItem(item, empty);

		if (!empty && item != null) {
			LayerBase layerBase = item.getLayerBase();

			String name = "-";

			if (layerBase instanceof EffectLayer) {
				EffectLayer effectLayer = (EffectLayer) layerBase;
				name = "\uf005 ";
				name += effectLayer.getEffectLayerInformation().getName()
						.getValue();
			} else if (layerBase instanceof EffectGroupLayer) {
				EffectGroupLayer effectGroupLayer = (EffectGroupLayer) layerBase;
				name = "\uf07b ";
				name += effectGroupLayer.getEffectLayerInformation().getName()
						.getValue();
			}

			setText(name);
		} else {
			setGraphic(null);
			setText(null);
		}

	}

	private void onDragDetected(MouseEvent event) {
		LayerBaseContainer<? extends LayerBase> layerBaseContainer = getItem();

		if (layerBaseContainer != null) {
			LayerBase layerBase = layerBaseContainer.getLayerBase();

			Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
			dragboard.setDragView(snapshot(null, null), 10, 10);

			Map<DataFormat, Object> data = new HashMap<>();
			String name = LayerBaseUtil.getName(layerBase);

			data.put(LAYER_DATA_FORMAT, name);
			data.put(DataFormat.PLAIN_TEXT, name);

			LayerTreeCellDragboard.setCurrentlyDraggedLayerTreeCell(this);

			dragboard.setContent(data);
		}

		event.consume();
	}

	private void onDragOver(DragEvent event) {
		LayerBaseContainer<? extends LayerBase> layerBaseContainer = getItem();

		if (layerBaseContainer != null) {
			if (isLayerPresentInDragboard(event.getDragboard())
					&& !LayerTreeCellDragboard.isLayerDragged(this)) {

				double y = event.getY();
				double height = getHeight();
				clearDragStyles();

				if (layerBaseContainer
						.getLayerBase() instanceof EffectGroupLayer) {
					String style = y < height / 4
							? DRAG_ABOVE
							: y < height * 3 / 4 ? DRAG_INSIDE : DRAG_BELOW;
					getStyleClass().add(style);
				} else {
					boolean above = y < getHeight() / 2;
					getStyleClass().add(above ? DRAG_ABOVE : DRAG_BELOW);
				}

				event.acceptTransferModes(TransferMode.MOVE);
			}
		}

		event.consume();

	}

	private void onDragExited(DragEvent event) {
		clearDragStyles();
		event.consume();
	}

	private void onDragDropped(DragEvent event) {
		TreeCell<LayerBaseContainer<? extends LayerBase>> currentlyDraggedLayerTreeCell = LayerTreeCellDragboard
				.getCurrentlyDraggedLayerTreeCell();

		LayerBaseContainer<? extends LayerBase> targetItem = getItem();
		LayerBase targetLayer = targetItem.getLayerBase();

		if (currentlyDraggedLayerTreeCell != null) {
			double y = event.getY();
			double height = getHeight();
			boolean above = y < height / 2;

			TreeItem<LayerBaseContainer<? extends LayerBase>> targetParent = this
					.getTreeItem().getParent();
			ObservableList<TreeItem<LayerBaseContainer<? extends LayerBase>>> targetParentChildren = targetParent
					.getChildren();

			TreeItem<LayerBaseContainer<? extends LayerBase>> draggedItem = currentlyDraggedLayerTreeCell
					.getTreeItem();
			TreeItem<LayerBaseContainer<? extends LayerBase>> draggedParent = draggedItem
					.getParent();
			ObservableList<TreeItem<LayerBaseContainer<? extends LayerBase>>> draggedChildren = draggedParent
					.getChildren();

			if (targetLayer instanceof EffectGroupLayer
					&& (y >= height / 4 && y < height * 3 / 4)) {
				System.out.println("aha in group hmmm");
				System.out.println("INSIDE AHA");
				// inside group
				ObservableList<TreeItem<LayerBaseContainer<? extends LayerBase>>> groupChildren = getTreeItem()
						.getChildren();
				if (!groupChildren.equals(draggedChildren)) {
					draggedChildren.remove(draggedItem);
					DelayedExecuter.execute(100,
							() -> groupChildren.add(0, draggedItem));
				}
			} else {
				System.out.println("SWAPPY SWAPPY!");
				ListItemSwapUtil.swapItems(draggedChildren,
						targetParentChildren, draggedItem, this.getTreeItem(),
						above);
			}
		}

		// TODO: drag on treeview = last index of root

	}

	private void onDragDone(DragEvent event) {
		LayerTreeCellDragboard.setCurrentlyDraggedLayerTreeCell(null);
	}

	private boolean isLayerPresentInDragboard(Dragboard dragboard) {
		return dragboard.getContent(LAYER_DATA_FORMAT) != null;
	}

	private void clearDragStyles() {
		getStyleClass().remove(DRAG_BELOW);
		getStyleClass().remove(DRAG_ABOVE);
		getStyleClass().remove(DRAG_INSIDE);
	}

}
