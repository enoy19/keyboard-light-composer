package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;

@FactoryGenericType(Float.class)
@Name("External Float")
public class ExternalFloat extends ExternalValue<Float> {

	private static final long serialVersionUID = 5196685170471572004L;

	@Override
    protected Class<Float> getType() {
        return Float.class;
    }

    @Override
    public Float getValue() {
        Float externalValue = getExternalValue();
		return externalValue;
    }

}
