package org.enoy.klc.control.external;

public class ExternalValueBoolean extends ExternalValue<Boolean> {

    public ExternalValueBoolean() {

    }

    public ExternalValueBoolean(Boolean data) {
        super(data);
    }

    @Override
    public final ExternalValueDataType getDataType() {
        return ExternalValueDataType.BOOLEAN;
    }

    @Override
    public Class<Boolean> getType() {
        return Boolean.class;
    }

}
