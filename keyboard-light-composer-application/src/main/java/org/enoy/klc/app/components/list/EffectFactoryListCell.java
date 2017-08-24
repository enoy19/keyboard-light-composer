package org.enoy.klc.app.components.list;

import javafx.scene.control.ListCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import org.enoy.klc.app.components.tree.EffectLayerContainer;
import org.enoy.klc.app.components.tree.LayerBaseContainer;
import org.enoy.klc.app.components.tree.LayerBaseTreeCell;
import org.enoy.klc.app.components.tree.LayerTreeCellDragboard;
import org.enoy.klc.app.components.utils.LayerTreeItemListenerUtil;
import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.effects.EffectFactory;
import org.enoy.klc.common.factories.Factory;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;

import java.util.HashMap;
import java.util.Map;

public class EffectFactoryListCell extends ListCell<EffectFactory> {

	public EffectFactoryListCell() {

		setOnDragDetected(this::onDragDetected);

	}

	@Override
	protected void updateItem(EffectFactory item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty && item != null) {
			setText(item.getName());
		} else {
			setText(null);
		}
	}

	private void onDragDetected(MouseEvent event) {

		Factory<Effect> factory = getItem();

		if (factory != null) {

			Effect effect = factory.create();
			EffectLayer effectLayer = new EffectLayer(effect);
			LayerBaseContainer<? extends LayerBase> layerBaseContainer = new EffectLayerContainer(effectLayer);

			if (layerBaseContainer != null) {
				Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
				dragboard.setDragView(snapshot(null, null), 10, 10);

				Map<DataFormat, Object> data = new HashMap<>();

				String name = factory.getName();
				data.put(LayerBaseTreeCell.LAYER_DATA_FORMAT, name);
				data.put(DataFormat.PLAIN_TEXT, name);

				TreeItem<LayerBaseContainer<? extends LayerBase>> treeItem = new TreeItem<>(layerBaseContainer);
				LayerTreeItemListenerUtil.addListenersToTreeItem(treeItem);
				LayerTreeCellDragboard.setCurrentlyDraggedLayerTreeItem(treeItem);

				dragboard.setContent(data);
			}

		}

		event.consume();

	}

}
