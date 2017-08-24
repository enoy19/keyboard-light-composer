package org.enoy.klc.app.components.property.editors;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import org.enoy.klc.app.components.PropertyValueEditor;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleMode;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleModeFactory;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleModeRegister;

@PropertyValueEditorTypeClass(ScaleMode.class)
public class ScaleModePropertyValueEditor extends PropertyValueEditor<ScaleMode> {

	private ComboBox<ScaleModeFactory> scaleModeFactories;
	private ScaleMode scaleBlendMode;

	public ScaleModePropertyValueEditor() {
		scaleModeFactories.setOnAction(e -> {
			scaleBlendMode = scaleModeFactories.getSelectionModel().getSelectedItem().create();
			updateValue();
		});
	}

	@Override
	public ScaleMode getValue() {
		return scaleBlendMode;
	}

	@Override
	public void initEditorValue(ScaleMode value) {
		Class<?> bmClass = value.getClass();
		scaleModeFactories.getItems()//
				.stream()//
				.filter(bmf -> bmf.getFactoryType().equals(bmClass)).findAny().ifPresent(bmf -> {
					scaleModeFactories.getSelectionModel().select(bmf);
				});
	}

	@Override
	protected Node initContent() {
		scaleModeFactories = new ComboBox<>();
		scaleModeFactories.getItems().setAll(ScaleModeRegister.getInstance().getRegisteredObjectsAsList());
		return scaleModeFactories;
	}

}
