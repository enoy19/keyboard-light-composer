package org.enoy.klc.app.components;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.enoy.klc.app.components.list.DeviceListCell;
import org.enoy.klc.common.device.Device;
import org.enoy.klc.common.device.DeviceRegister;
import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.control.effects.LayerRenderer;
import org.enoy.klc.control.updater.Updater;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;

public class KlcApplication implements Initializable {

	// TODO: refactor to type prefix
	@FXML
	private StackPane layerPropertiesEditorPane;

	@FXML
	private EffectLayers effectLayersController;

	@FXML
	private ComboBox<Device> comboBoxDevice;

	@FXML
	private ToggleButton toggleButtonPlay;

	@FXML
	private SplitPane splitPaneEffectsAndLayers;

	@FXML
	private StackPane stackPaneEffectProperties;

	private KlcPropertyContainerEditor layerPropertiesEditor;

	private KlcPropertyContainerEditor effectPropertiesEditor;

	private Device currentDevice;

	private Updater updater;

	private LayerRenderer layerRenderer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updater = new Updater();
		layerPropertiesEditor = new KlcPropertyContainerEditor();
		effectPropertiesEditor = new KlcPropertyContainerEditor();
		layerPropertiesEditorPane.getChildren().add(layerPropertiesEditor);
		stackPaneEffectProperties.getChildren().add(effectPropertiesEditor);
		effectLayersController.setSelectListener(this::onEffectLayerSelect);

		comboBoxDevice.disableProperty().bind(toggleButtonPlay.selectedProperty());
		toggleButtonPlay.disableProperty().bind(comboBoxDevice.getSelectionModel().selectedItemProperty().isNull());
		splitPaneEffectsAndLayers.disableProperty().bind(toggleButtonPlay.selectedProperty());
		layerPropertiesEditorPane.disableProperty().bind(toggleButtonPlay.selectedProperty());

		comboBoxDevice.getItems().addAll(DeviceRegister.getInstance().getRegisteredObjectsAsList());
		comboBoxDevice.setCellFactory(lv -> new DeviceListCell());

	}

	@FXML
	private void play() {
		boolean selected = toggleButtonPlay.isSelected();
		updateToggleButtonText();

		if (selected) {
			layerRenderer.setRenderables(Arrays.asList(effectLayersController.getRoot()));
		}

		updater.setPaused(!selected);
		layerRenderer.setPaused(!selected);
	}

	private void updateToggleButtonText() {
		String text = toggleButtonPlay.isSelected() ? "\uf04d" : "\uf04b";
		toggleButtonPlay.setText(text);
	}

	@FXML
	private void deviceChanged() {
		if (currentDevice != null) {
			currentDevice.shutdown();
		}
		currentDevice = comboBoxDevice.getValue();
		currentDevice.init();
		layerRenderer.setDevice(currentDevice);
	}

	public void setUpdater(Updater updater) {
		if (this.updater != null) {
			updater.setOnPause(null);
		}
		this.updater = updater;
		this.updater.setOnPause(this::onPause);
	}

	public void setRenderer(LayerRenderer layerRenderer) {
		if (this.layerRenderer != null) {
			layerRenderer.setOnPause(null);
		}
		this.layerRenderer = layerRenderer;
		this.layerRenderer.setOnPause(this::onPause);
	}

	private void onEffectLayerSelect(LayerBase layerBase) {
		layerPropertiesEditor.setPropertyContainer(layerBase);
		Effect effect;
		if (layerBase instanceof EffectLayer
				&& (effect = ((EffectLayer) layerBase).getEffect()) instanceof KlcPropertyContainer) {
			effectPropertiesEditor.setPropertyContainer((KlcPropertyContainer) effect);
			stackPaneEffectProperties.setVisible(true);
		}else{
			stackPaneEffectProperties.setVisible(false);
		}
	}

	private void onPause(boolean pause) {
		if (pause) {
			toggleButtonPlay.setSelected(false);
			updater.setPaused(true);
			layerRenderer.setPaused(true);
			updateToggleButtonText();
		}
	}

}
