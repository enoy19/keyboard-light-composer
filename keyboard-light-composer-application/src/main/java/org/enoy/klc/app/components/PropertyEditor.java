package org.enoy.klc.app.components;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.enoy.klc.app.components.property.editors.PropertyValueEditorFactory;
import org.enoy.klc.app.components.property.editors.PropertyValueEditorRegister;
import org.enoy.klc.app.components.utils.DialogUtil;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategyFactory;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategyRegister;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class PropertyEditor extends HBox implements Initializable {

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
	private StackPane stackPanePropertyValueEditor;

	@FXML
	private HBox hBoxValueStrategy;

	private KlcWritableProperty<?> property;

	public PropertyEditor() {
		FXMLLoaderUtil.loadRootControllerFXMLDocument(this,
				"/fxml/PropertyEditor.fxml", "fxml/i18n/klc");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setValueStrategyVisible(false);
	}

	@FXML
	private void openValueStrategyProperties(ActionEvent event) {

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@FXML
	private void openValueStrategySelector(ActionEvent event) {
		List<ValueStrategyFactory<?>> strategies = ValueStrategyRegister
				.getInstance()
				.getValueStrategiesFor(property.getPropertyType());

		if (strategies.size() > 0) {
			ValueStrategyFactory selectedFactory = DialogUtil.select(
					"Select Value Strategy", null, "Select Value Factory: ",
					strategies);

			if (selectedFactory != null) {
				ValueStrategy valueStrategy = selectedFactory
						.createValueStrategy();
				property.setValueStrategy(valueStrategy);
				setValueStrategyVisible(true);
			}
		} else {
			DialogUtil.alert("No Value Strategy found", null,
					"No value strategy was found for that property type");
		}
	}

	public void setKlcProperty(KlcWritableProperty<?> property) {
		this.property = property;
		labelPropertyName.setText(property.getName());
		tooltipPropertyDescription.setText(property.getDescription());
		buttonValueStrategySelector
				.setVisible(property.isValueStrategyAllowed());
		setValueStrategyVisible(property.isValueStrategyPresent());
		setValueEditor(property);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void setValueEditor(KlcWritableProperty<?> property) {
		Node valueEditor;

		PropertyValueEditorFactory<?> propertyValueEditorFactory = PropertyValueEditorRegister
				.getInstance().get(property.getPropertyType());
		if (propertyValueEditorFactory != null) {
			valueEditor = propertyValueEditorFactory
					.createPropertyValueEditor();
			((PropertyValueEditor) valueEditor).setKlcProperty(property);
		} else {
			// no property value factory available
			valueEditor = new Label(property.getValue().toString());
		}

		stackPanePropertyValueEditor.getChildren().clear();
		stackPanePropertyValueEditor.getChildren().add(valueEditor);
	}

	private void setValueStrategyVisible(boolean visible) {
		stackPanePropertyValueEditor.setVisible(!visible);
		hBoxValueStrategy.setVisible(visible);

		updateValueStrategyControls();
	}

	private void updateValueStrategyControls() {
		if (property != null) {
			ValueStrategy<?> valueStrategy = property.getPropertyValue()
					.getValueStrategy();
			if (valueStrategy != null) {
				labelValueStrategyName
						.setText(valueStrategy.getClass().getSimpleName());
				// TODO: group property container
				buttonValueStrategyProperties.setVisible(
						valueStrategy instanceof KlcPropertyContainer);
			} else {
				labelValueStrategyName.setText(null);
				buttonValueStrategyProperties.setVisible(false);
			}
		}
	}

}
