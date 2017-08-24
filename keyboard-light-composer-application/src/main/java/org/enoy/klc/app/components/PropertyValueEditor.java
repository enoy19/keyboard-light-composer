package org.enoy.klc.app.components;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.enoy.klc.common.properties.KlcWritableProperty;

import java.io.Serializable;

public abstract class PropertyValueEditor<T extends Serializable> extends StackPane {

	private KlcWritableProperty<T> property;

	public PropertyValueEditor() {
		ObservableList<Node> children = getChildren();
		children.add(initContent());
	}

	public void setKlcProperty(KlcWritableProperty<T> property) {
		this.property = property;
		initEditorValue(property.getValue());
	}

	public final KlcWritableProperty<T> getProperty() {
		return property;
	}

	public void updateValue() {
		property.setValue(getValue());
	}

	public abstract T getValue();

	public abstract void initEditorValue(T value);

	protected abstract Node initContent();
	
}
