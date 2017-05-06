package org.enoy.klc.app.components.property.editors;

import java.io.Serializable;

import org.enoy.klc.app.components.PropertyValueEditor;

// TODO: use Factory<T>
public class PropertyValueEditorFactory<T extends Serializable> {

	private Class<? extends PropertyValueEditor<T>> propertValueEditorClass;
	private Class<T> valueClass;

	public PropertyValueEditorFactory(Class<T> valueClass,
			Class<? extends PropertyValueEditor<T>> propertValueEditorClass) {
		this.propertValueEditorClass = propertValueEditorClass;
		this.valueClass = valueClass;
	}

	public PropertyValueEditor<T> createPropertyValueEditor() {
		try {
			return propertValueEditorClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Class<? extends PropertyValueEditor<T>> getPropertValueEditorClass() {
		return propertValueEditorClass;
	}

	public Class<T> getValueClass() {
		return valueClass;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static PropertyValueEditorFactory<?> createPropertyValueEditorFactory(
			Class<? extends PropertyValueEditor> pveClass) {
		Class<? extends PropertyValueEditor<?>> clazz = (Class<? extends PropertyValueEditor<?>>) pveClass;
		PropertyValueEditorTypeClass typeClass = clazz
				.getAnnotation(PropertyValueEditorTypeClass.class);
		if (typeClass == null) {
			throw new RuntimeException("property value editors must have a "
					+ PropertyValueEditorTypeClass.class.getName()
					+ " annotation with the edit type: " + pveClass.getName());
		}
		Class<?> typeClassValue = typeClass.value();
		PropertyValueEditorFactory factory = new PropertyValueEditorFactory(
				typeClassValue, clazz);
		return factory;
	}

}
