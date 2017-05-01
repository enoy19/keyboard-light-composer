package org.enoy.klc.app.components.property.editors;

@PropertyValueEditorTypeClass(Integer.class)
public class IntegerPropertyValueEditor extends NumberPropertyValueEditor<Integer>{

	@Override
	public Integer getValue() {
		return getTextFieldNumber().intValue();
	}

}
