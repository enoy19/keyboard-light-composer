package org.enoy.klc.app.components.tree;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import org.enoy.klc.common.layers.LayerBase;

public class LayerBaseTreeItem<T extends LayerBase> extends TreeItem<T> {

	public LayerBaseTreeItem(T value, Node graphic) {
		super(value, graphic);
	}

	public LayerBaseTreeItem(T value) {
		super(value);
	}

}
