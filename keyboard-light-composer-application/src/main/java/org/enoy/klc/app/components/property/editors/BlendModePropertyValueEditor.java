package org.enoy.klc.app.components.property.editors;

import org.enoy.klc.app.components.PropertyValueEditor;
import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeFactory;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeRegister;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;

// not finished uncomment below when done
// @PropertyValueEditorTypeClass(BlendMode.class)
public class BlendModePropertyValueEditor extends PropertyValueEditor<BlendMode>{

	private ComboBox<BlendModeFactory> blendModeFactories;
	private BlendMode currentBlendMode;
	
	@Override
	public BlendMode getValue() {
		return currentBlendMode;
	}

	@Override
	public void initEditorValue(BlendMode value) {
		// TODO: get factory of value
	}

	@Override
	protected Node initContent() {
		blendModeFactories = new ComboBox<>();
		blendModeFactories.getItems().setAll(BlendModeRegister.getInstance().getRegisteredObjectsAsList());
		return blendModeFactories;
	}

	
	
}
