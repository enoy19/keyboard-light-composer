package org.enoy.klc.app.components.property.editors;

import org.enoy.klc.app.components.PropertyValueEditor;
import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeFactory;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeRegister;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;

@PropertyValueEditorTypeClass(BlendMode.class)
public class BlendModePropertyValueEditor extends PropertyValueEditor<BlendMode> {

	private ComboBox<BlendModeFactory> blendModeFactories;
	private BlendMode currentBlendMode;

	public BlendModePropertyValueEditor() {
		blendModeFactories.setOnAction(e -> {
			currentBlendMode = blendModeFactories.getSelectionModel().getSelectedItem().create();
			updateValue();
		});
	}

	@Override
	public BlendMode getValue() {
		return currentBlendMode;
	}

	@Override
	public void initEditorValue(BlendMode value) {
		Class<?> bmClass = value.getClass();
		blendModeFactories.getItems()//
				.stream()//
				.filter(bmf -> bmf.getFactoryType().equals(bmClass)).findAny().ifPresent(bmf -> {
					blendModeFactories.getSelectionModel().select(bmf);
				});
	}

	@Override
	protected Node initContent() {
		blendModeFactories = new ComboBox<>();
		blendModeFactories.getItems().setAll(BlendModeRegister.getInstance().getRegisteredObjectsAsList());
		return blendModeFactories;
	}

}
