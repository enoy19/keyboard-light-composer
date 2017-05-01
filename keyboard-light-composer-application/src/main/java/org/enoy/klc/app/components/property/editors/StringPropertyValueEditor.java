package org.enoy.klc.app.components.property.editors;

import org.enoy.klc.app.components.PropertyValueEditor;
import org.enoy.klc.app.components.PropertyValueEditor.PropertyValueEditorTypeClass;

import javafx.scene.Node;
import javafx.scene.control.TextField;

@PropertyValueEditorTypeClass(String.class)
public class StringPropertyValueEditor extends PropertyValueEditor<String> {

	private TextField textField;

	public StringPropertyValueEditor() {
		textField.textProperty().addListener((v, o, n) -> updateValue());
	}

	@Override
	public String getValue() {
		return textField.getText();
	}

	@Override
	public void initEditorValue(String value) {
		textField.setText(value);
	}

	@Override
	protected Node initContent() {
		textField = new TextField();
		return textField;
	}

}
