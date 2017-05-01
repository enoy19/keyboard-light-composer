package org.enoy.klc.app.components;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class KlcApplication implements Initializable {

	@FXML
	private StackPane layerPropertiesEditorPane;
	
	@FXML
	private EffectLayers effectLayersController;
	
	private KlcPropertyContainerEditor layerPropertiesEditor;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		layerPropertiesEditor = new KlcPropertyContainerEditor();
		layerPropertiesEditorPane.getChildren().add(layerPropertiesEditor);
		effectLayersController.setLayerPropertiesEditor(layerPropertiesEditor);
	}

}
