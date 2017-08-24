package org.enoy.klc.control.external;

public class ExternalValueLong extends ExternalValueNumber<Long> {

	public ExternalValueLong() {
		
	}

	public ExternalValueLong(Long data) {
		super(data);
	}

	@Override
	public ExternalValueDataType getDataType() {
		return ExternalValueDataType.LONG;
	}

	@Override
	public Class<Long> getType() {
		return Long.class;
	}

}
