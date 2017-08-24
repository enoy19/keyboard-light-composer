package org.enoy.klc.control.external;

public class ExternalValueFloat extends ExternalValueNumber<Float> {

	public ExternalValueFloat() {

	}

	public ExternalValueFloat(Float data) {
		super(data);
	}

	@Override
	public ExternalValueDataType getDataType() {
		return ExternalValueDataType.FLOAT;
	}

	@Override
	public Class<Float> getType() {
		return Float.class;
	}

}
