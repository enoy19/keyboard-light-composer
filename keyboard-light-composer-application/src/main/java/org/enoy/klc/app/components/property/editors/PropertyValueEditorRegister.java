package org.enoy.klc.app.components.property.editors;

import java.util.HashMap;
import java.util.Map;

import org.enoy.klc.common.Register;

public class PropertyValueEditorRegister extends Register<PropertyValueEditorFactory<?>> {

	private static PropertyValueEditorRegister instance;

	private static final Map<String, PropertyValueEditorFactory<?>> FACTORIES = new HashMap<>();

	private PropertyValueEditorRegister() {

	}

	@Override
	public void register(PropertyValueEditorFactory<?> object) {
		super.register(object);
		
		FACTORIES.put(object.getPropertValueEditorClass().getName(), object);
	}

	
	public PropertyValueEditorFactory<?> get(Class<?> propertyValueType) {
		return FACTORIES.get(propertyValueType);
	}

	public static PropertyValueEditorRegister getInstance() {
		return instance == null ? instance = new PropertyValueEditorRegister() : instance;
	}

}
