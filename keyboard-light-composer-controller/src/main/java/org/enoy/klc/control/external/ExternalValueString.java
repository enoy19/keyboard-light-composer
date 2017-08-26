package org.enoy.klc.control.external;

public class ExternalValueString extends ExternalValue<String> {

	public ExternalValueString() {

	}

	public ExternalValueString(String data) {
		super(data);
	}

	@Override
	public ExternalValueDataType getDataType() {
		return ExternalValueDataType.STRING;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

}
