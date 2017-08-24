package org.enoy.klc.app.components.property.editors;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import org.enoy.klc.app.components.PropertyValueEditor;
import org.enoy.klc.common.model.KlcColor;

@PropertyValueEditorTypeClass(KlcColor.class)
public class ColorPropertyValueEditor extends PropertyValueEditor<KlcColor>{

	private ColorPicker colorPicker;
	
	public ColorPropertyValueEditor() {
		colorPicker.setOnAction(e->updateValue());
	}
	
	@Override
	public KlcColor getValue() {
		return new KlcColor(colorPicker.getValue());
	}

	@Override
	public void initEditorValue(KlcColor value) {
		colorPicker.setValue(value.getColor());
	}

	@Override
	protected Node initContent() {
		colorPicker = new ColorPicker();
		return colorPicker;
	}

}
