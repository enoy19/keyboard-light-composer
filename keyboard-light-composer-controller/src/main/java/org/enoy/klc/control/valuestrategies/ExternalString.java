package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.factories.FactoryGenericType;
import org.enoy.klc.common.factories.Name;

@FactoryGenericType(String.class)
@Name("External String")
public class ExternalString extends ExternalValue<String> {

	private static final long serialVersionUID = 8510362143638267070L;

	@Override
    protected Class<String> getType() {
        return String.class;
    }

    @Override
    public String getValue() {
        return getExternalValue();
    }

}
