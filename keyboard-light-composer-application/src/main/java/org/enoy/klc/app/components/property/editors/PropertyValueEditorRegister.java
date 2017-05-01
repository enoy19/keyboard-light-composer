package org.enoy.klc.app.components.property.editors;

import org.enoy.klc.common.Register;

public class PropertyValueEditorRegister
		extends
			Register<PropertyValueEditorFactory<?>> {

	private static PropertyValueEditorRegister instance;

	private PropertyValueEditorRegister() {

	}

	public PropertyValueEditorFactory<?> get(Class<?> propertyValueType) {
		return getRegisteredStream()
				.filter(f -> f.getValueClass().equals(propertyValueType))
				.findFirst().orElse(null);
	}

	public static PropertyValueEditorRegister getInstance() {
		return instance == null
				? instance = new PropertyValueEditorRegister()
				: instance;
	}

}
