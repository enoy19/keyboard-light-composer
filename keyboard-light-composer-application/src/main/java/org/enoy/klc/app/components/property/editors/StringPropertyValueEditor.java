package org.enoy.klc.app.components.property.editors;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.enoy.klc.app.components.PropertyValueEditor;

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
