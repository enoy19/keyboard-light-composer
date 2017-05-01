package org.enoy.klc.app.components.property.editors;

@PropertyValueEditorTypeClass(Float.class)
public class FloatPropertyValueEditor extends NumberPropertyValueEditor<Float>{

	@Override
	public Float getValue() {
		return getTextFieldNumber().floatValue();
	}

}
