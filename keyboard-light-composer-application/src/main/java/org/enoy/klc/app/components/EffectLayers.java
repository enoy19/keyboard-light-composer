package org.enoy.klc.app.components;

import java.net.URL;
import java.util.ResourceBundle;

import org.enoy.klc.app.components.tree.EffectGroupLayerContainer;
import org.enoy.klc.app.components.tree.LayerBaseContainer;
import org.enoy.klc.app.components.tree.LayerBaseTreeCell;
import org.enoy.klc.app.components.utils.DialogUtil;
import org.enoy.klc.app.components.utils.LayerTreeItemListenerUtil;
import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
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

	private KlcPropertyContainerEditor layerPropertiesEditor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		treeViewLayers.setCellFactory(treeView -> new LayerBaseTreeCell());
		treeViewLayers.setShowRoot(false);
		getSelectionModel()
				.setSelectionMode(SelectionMode.SINGLE);

		getSelectionModel().selectedItemProperty()
				.addListener((v, o, n) -> {
					if(n != null){
						LayerBase layerBase = n.getValue().getLayerBase();
						layerPropertiesEditor.setPropertyContainer(layerBase);
					}else{
						layerPropertiesEditor.setPropertyContainer(null);
					}
				});

		resetTreeView();
	}

	private MultipleSelectionModel<TreeItem<LayerBaseContainer<? extends LayerBase>>> getSelectionModel() {
		return treeViewLayers.getSelectionModel();
	}

	@FXML
	private void delete(ActionEvent event) {
		// TODO: close property editor!
		int selectedIndex = getSelectionModel().getSelectedIndex();
		DialogUtil.confirm("Confirmation", null,
				"Effect Layer will be deleted.", this::deleteSelectedTreeItem);
		getSelectionModel().clearAndSelect(selectedIndex);
		
	}

	private void deleteSelectedTreeItem() {
		TreeItem<LayerBaseContainer<? extends LayerBase>> selectedItem = getSelectedItem();
		selectedItem.getParent().getChildren().remove(selectedItem);
	}

	private TreeItem<LayerBaseContainer<? extends LayerBase>> getSelectedItem() {
		return getSelectionModel().getSelectedItem();
	}

	@FXML
	private void addFolder(ActionEvent event) {
		TreeItem<LayerBaseContainer<? extends LayerBase>> selectedItem = getSelectedItem();
		TreeItem<LayerBaseContainer<? extends LayerBase>> effectGroup = createEffectGroup();

		if (selectedItem != null) {
			LayerBase layerBase = selectedItem.getValue().getLayerBase();
			if (layerBase instanceof EffectLayer) {
				selectedItem.getParent().getChildren().add(effectGroup);
			} else if (layerBase instanceof EffectGroupLayer) {
				selectedItem.getChildren().add(effectGroup);
			}
		} else {
			treeRoot.getChildren().add(effectGroup);
		}

	}

	private void resetTreeView() {

		root = new EffectGroupLayer("Root");
		treeRoot = new TreeItem<>(new EffectGroupLayerContainer(root));
		treeViewLayers.setRoot(treeRoot);

	}

	private TreeItem<LayerBaseContainer<? extends LayerBase>> createEffectGroup() {
		TreeItem<LayerBaseContainer<? extends LayerBase>> effectGroupTreeItem = new TreeItem<LayerBaseContainer<? extends LayerBase>>(
				new LayerBaseContainer<LayerBase>(
						new EffectGroupLayer("New Group")));
		LayerTreeItemListenerUtil.addListenersToTreeItem(effectGroupTreeItem);
		return effectGroupTreeItem;
	}

	public void setLayerPropertiesEditor(
			KlcPropertyContainerEditor layerPropertiesEditor) {
		this.layerPropertiesEditor = layerPropertiesEditor;
	}
	
	public EffectGroupLayer getRoot() {
		return root;
	}

}
