package org.enoy.klc.app.components.property.editors;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import org.enoy.klc.app.components.PropertyValueEditor;

@PropertyValueEditorTypeClass(Boolean.class)
public class BooleanPropertyValueEditor extends PropertyValueEditor<Boolean> {

	private CheckBox checkBox;

	public BooleanPropertyValueEditor() {
		checkBox.selectedProperty().addListener((v, o, n) -> updateValue());
	}

	@Override
	public Boolean getValue() {
		return checkBox.isSelected();
	}

	@Override
	public void initEditorValue(Boolean value) {
		checkBox.setSelected(value);
	}

	@Override
	protected Node initContent() {
		checkBox = new CheckBox();
		return checkBox;
	}

}
