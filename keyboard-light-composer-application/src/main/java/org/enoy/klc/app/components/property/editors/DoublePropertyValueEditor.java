package org.enoy.klc.app.components.property.editors;

@PropertyValueEditorTypeClass(Double.class)
public class DoublePropertyValueEditor extends NumberPropertyValueEditor<Double>{

	@Override
	public Double getValue() {
		return getTextFieldNumber().doubleValue();
	}

}
