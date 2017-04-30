package org.enoy.klc.app.components.tree;

import java.util.HashMap;
import java.util.Map;

import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.control.utils.LayerBaseUtil;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class LayerBaseTreeCell extends TreeCell<LayerBaseContainer<? extends LayerBase>> {

	private static final DataFormat LAYER_DATA_FORMAT = new DataFormat(LayerBase.class.getName());

	public LayerBaseTreeCell() {

		setOnDragOver(this::onDragOver);
		setOnDragDetected(this::onDragDetected);
		setOnDragEntered(this::onDragEntered);
		setOnDragExited(this::onDragExited);
		setOnDragDropped(this::onDragDropped);
		setOnDragDone(this::onDragDone);

	}

	@Override
	protected void updateItem(LayerBaseContainer<? extends LayerBase> item, boolean empty) {
		super.updateItem(item, empty);

		if (!empty && item != null) {
			LayerBase layerBase = item.getLayerBase();

			String name = "-";

			if (layerBase instanceof EffectLayer) {
				EffectLayer effectLayer = (EffectLayer) layerBase;
				name = effectLayer.getEffectLayerInformation().getName().getValue();
			} else if (layerBase instanceof EffectGroupLayer) {
				EffectGroupLayer effectGroupLayer = (EffectGroupLayer) layerBase;
				name = effectGroupLayer.getEffectLayerInformation().getName().getValue();
			}

			setText(name);
		} else {
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
			if (isLayerPresentInDragboard(event.getDragboard()) && !LayerTreeCellDragboard.isLayerDragged(this)) {

				boolean above = event.getY() < getHeight() / 2;

				clearDragStyles();
				getStyleClass().add(above ? "drag-above-line" : "drag-below-line");

				event.acceptTransferModes(TransferMode.MOVE);
			}
		}

		event.consume();

	}

	private void onDragEntered(DragEvent event) {
		System.out.println("Drag Entered " + this);

		LayerBaseContainer<? extends LayerBase> layerBaseContainer = getItem();

		if (layerBaseContainer != null) {
			if (isLayerPresentInDragboard(event.getDragboard())) {

			}
		}

		event.consume();
	}

	private void onDragExited(DragEvent event) {
		clearDragStyles();
		event.consume();
	}

	private void onDragDropped(DragEvent event) {
		System.out.println("Drag Dropped " + this);

		boolean above = event.getY() < getHeight() / 2;

		TreeCell<LayerBaseContainer<? extends LayerBase>> currentlyDraggedLayerTreeCell = LayerTreeCellDragboard
				.getCurrentlyDraggedLayerTreeCell();
		if (currentlyDraggedLayerTreeCell != null) {

			TreeItem<LayerBaseContainer<? extends LayerBase>> targetParent = this.getTreeItem().getParent();
			ObservableList<TreeItem<LayerBaseContainer<? extends LayerBase>>> targetParentChildren = targetParent.getChildren();
			int targetPosition = targetParentChildren.indexOf(this.getTreeItem());
			int draggedNewPosition = targetPosition + (above ? -1 : 1);
			draggedNewPosition = Math.max(0, draggedNewPosition);

			TreeItem<LayerBaseContainer<? extends LayerBase>> draggedItem =  currentlyDraggedLayerTreeCell.getTreeItem();
			TreeItem<LayerBaseContainer<? extends LayerBase>> draggedParent = draggedItem.getParent();
			
			draggedParent.getChildren().remove(draggedItem);
			if(draggedNewPosition > targetParentChildren.size()){
;				draggedNewPosition = targetParentChildren.size();
			}
			
			targetParentChildren.add(draggedNewPosition, draggedItem);

		}

	}

	private void onDragDone(DragEvent event) {
		System.out.println("Drag Done " + this);
	}

	private boolean isLayerPresentInDragboard(Dragboard dragboard) {
		return dragboard.getContent(LAYER_DATA_FORMAT) != null;
	}

	private void clearDragStyles() {
		getStyleClass().remove("drag-below-line");
		getStyleClass().remove("drag-above-line");
	}

}
