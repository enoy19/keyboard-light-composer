package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;

@FactoryGenericType(Boolean.class)
@Name("External Boolean")
public class ExternalBoolean extends ExternalValue<Boolean> {

	private static final long serialVersionUID = -4388835305379695481L;

	@Override
    protected Class<Boolean> getType() {
        return Boolean.class;
    }

    @Override
    public Boolean getValue() {
        return getExternalValue();
    }

}
