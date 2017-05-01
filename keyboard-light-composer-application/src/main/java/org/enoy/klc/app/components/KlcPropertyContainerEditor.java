package org.enoy.klc.app.components;

import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class KlcPropertyContainerEditor extends VBox {
	
	public KlcPropertyContainerEditor() {
		setSpacing(3);
		setPadding(new Insets(3));
	}

	public void setPropertyContainer(KlcPropertyContainer propertyContainer) {
		getChildren().clear();

		if (propertyContainer != null) {
			for (KlcWritableProperty<?> property : propertyContainer
					.getProperties()) {
				PropertyEditor editor = new PropertyEditor();
				editor.setKlcProperty(property);
				getChildren().add(editor);
			}
		}

	}

}
