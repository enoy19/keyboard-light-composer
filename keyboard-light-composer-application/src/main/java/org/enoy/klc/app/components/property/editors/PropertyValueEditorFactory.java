package org.enoy.klc.app.components.property.editors;

import org.enoy.klc.app.components.PropertyValueEditor;

public class PropertyValueEditorFactory<T extends PropertyValueEditor<?>> {

	private Class<T> propertValueEditorClass;

	public PropertyValueEditorFactory(Class<T> propertValueEditorClass) {
		this.propertValueEditorClass = propertValueEditorClass;
	}

	public PropertyValueEditor<?> createPropertyValueEditor() {
		try {
			return propertValueEditorClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Class<T> getPropertValueEditorClass() {
		return propertValueEditorClass;
	}

}
