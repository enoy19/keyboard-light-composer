package org.enoy.klc.control.external;

abstract class ExternalValueNumber<T extends Number> extends ExternalValue<T> {

    public ExternalValueNumber() {
    }

    public ExternalValueNumber(T data) {
        super(data);
    }

}