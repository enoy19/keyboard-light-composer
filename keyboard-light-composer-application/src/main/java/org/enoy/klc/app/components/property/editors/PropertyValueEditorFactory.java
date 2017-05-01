package org.enoy.klc.app.components.property.editors;

import org.enoy.klc.app.components.PropertyValueEditor;

public class PropertyValueEditorFactory<T> {

	private Class<? extends PropertyValueEditor<T>> propertValueEditorClass;
	private Class<T> valueClass;

	public PropertyValueEditorFactory(Class<T> valueClass, Class<? extends PropertyValueEditor<T>> propertValueEditorClass) {
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

}
