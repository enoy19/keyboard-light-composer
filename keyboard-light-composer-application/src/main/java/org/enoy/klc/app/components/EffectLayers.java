package org.enoy.klc.app.components;

import java.net.URL;
import java.util.ResourceBundle;

import org.enoy.klc.app.components.tree.EffectGroupLayerContainer;
import org.enoy.klc.app.components.tree.EffectLayerContainer;
import org.enoy.klc.app.components.tree.LayerBaseContainer;
import org.enoy.klc.app.components.tree.LayerBaseTreeCell;
import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.effects.standard.SolidColorEffect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class EffectLayers implements Initializable {

	@FXML
	private TreeView<LayerBaseContainer<? extends LayerBase>> treeViewLayers;

	@FXML
	private ToolBar toolBar;

	private EffectGroupLayer root;

	private TreeItem<LayerBaseContainer<? extends LayerBase>> treeRoot;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		treeViewLayers.setCellFactory(treeView -> new LayerBaseTreeCell());
		treeViewLayers.setShowRoot(false);

		resetTreeView();
	}

	@FXML
	private void delete(ActionEvent event) {

	}

	private void resetTreeView() {

		root = new EffectGroupLayer("Root");
		treeRoot = new TreeItem<>(new EffectGroupLayerContainer(root));
		treeViewLayers.setRoot(treeRoot);

		EffectLayerContainer value = new EffectLayerContainer(new EffectLayer(new SolidColorEffect()));
		TreeItem<LayerBaseContainer<?>> dummyLayerContainer = new TreeItem<>(value);

		value = new EffectLayerContainer(new EffectLayer(new SolidColorEffect()));
		((EffectLayer)value.getLayerBase()).getEffectLayerInformation().getName().setValue("TEST");
		
		TreeItem<LayerBaseContainer<?>> dummyLayerContainer2 = new TreeItem<>(value);
		
		

		treeRoot.getChildren().add(dummyLayerContainer);
		treeRoot.getChildren().add(dummyLayerContainer2);

	}

}
