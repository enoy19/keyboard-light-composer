package org.enoy.klc.app.components;

import org.enoy.klc.common.properties.KlcWritableProperty;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

public class PropertyEditor {

	@FXML
	private Label labelPropertyName;

	@FXML
	private Tooltip tooltipPropertyDescription;

	@FXML
	private Button buttonValueStrategySelector;

	@FXML
	private Label labelValueStrategyName;

	@FXML
	private Button buttonValueStrategyProperties;

	@FXML
	private Pane panePropertyValueEditor;
	
	private KlcWritableProperty<?> property;

	public PropertyEditor() {
		FXMLLoaderUtil.loadRootControllerFXMLDocument(this, "/fxml/PropertyEditor.fxml", "fxml/i18n/klc");
	}

	@FXML
	private void openValueStrategyProperties(ActionEvent event) {

	}

	@FXML
	private void openValueStrategySelector(ActionEvent event) {

	}

	public void setKlcProperty(KlcWritableProperty<?> property) {
		this.property = property;
	}

}
