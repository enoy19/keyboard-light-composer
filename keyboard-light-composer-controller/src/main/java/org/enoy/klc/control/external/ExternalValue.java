package org.enoy.klc.control.external;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.function.Function;

public abstract class ExternalValue<T> {

    private String parameter;
    private T data;

    ExternalValue() {

    }

    ExternalValue(T data) {
        this.data = data;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public abstract ExternalValueDataType getDataType();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public abstract Class<T> getType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExternalValue<?> that = (ExternalValue<?>) o;

        return parameter != null ? parameter.equals(that.parameter) : that.parameter == null;
    }

    @Override
    public int hashCode() {
        return parameter != null ? parameter.hashCode() : 0;
    }

    public enum ExternalValueDataType {
        BOOLEAN(0, Boolean.class, Boolean::parseBoolean),
        FLOAT(1, Float.class, s -> {
            return parseNumber(s).floatValue();
        }),
        LONG(2, Long.class, s -> {
        	return parseNumber(s).longValue();
        }),
        STRING(3, String.class, s->s);

        private int dataTypeId;
        private Class<?> type;
        private Function<String, Object> converter;

        ExternalValueDataType(int dataTypeId, Class<?> type, Function<String, Object> converter) {
            this.dataTypeId = dataTypeId;
            this.type = type;
            this.converter = converter;
        }

        public int getDataTypeId() {
            return dataTypeId;
        }

        public Class<?> getType() {
            return type;
        }

        public static ExternalValueDataType getById(int id) {
            for (ExternalValueDataType externalValueDataType : values()) {
                if (externalValueDataType.dataTypeId == id) {
                    return externalValueDataType;
                }
            }

            return null;
        }

        public Object convert(String dataString) {
            return converter.apply(dataString);
        }
        
        private static Number parseNumber(String numString) {
        	try {
                return NumberFormat.getInstance(Locale.US).parse(numString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "parameter='" + parameter + '\'' +
                ", data=" + data +
                '}';
    }
}
