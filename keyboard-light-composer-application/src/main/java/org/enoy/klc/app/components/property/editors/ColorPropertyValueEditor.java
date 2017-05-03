package org.enoy.klc.app.components.property.editors;

import org.enoy.klc.app.components.PropertyValueEditor;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

@PropertyValueEditorTypeClass(Color.class)
public class ColorPropertyValueEditor extends PropertyValueEditor<Color>{

	private ColorPicker colorPicker;
	
	public ColorPropertyValueEditor() {
		colorPicker.setOnAction(e->updateValue());
	}
	
	@Override
	public Color getValue() {
		return colorPicker.getValue();
	}

	@Override
	public void initEditorValue(Color value) {
		colorPicker.setValue(value);
	}

	@Override
	protected Node initContent() {
		colorPicker = new ColorPicker();
		return colorPicker;
	}

}
