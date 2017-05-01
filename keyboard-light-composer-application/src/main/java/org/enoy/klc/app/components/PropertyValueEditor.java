package org.enoy.klc.app.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.enoy.klc.common.properties.KlcWritableProperty;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class PropertyValueEditor<T> extends Pane {

	private KlcWritableProperty<T> property;

	public PropertyValueEditor() {
		getChildren().add(getContent());
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

	protected abstract Node getContent();

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface PropertyValueEditorTypeClass {

		public Class<?> value();

	}

}
