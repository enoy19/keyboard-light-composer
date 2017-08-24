package org.enoy.klc.control.valuestrategies;

import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.IntegerKlcProperty;
import org.enoy.klc.common.properties.primitive.StringKlcProperty;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.updatables.DependentImpl;
import org.enoy.klc.control.external.ExternalValueContainer;

import java.io.Serializable;

public abstract class ExternalValue<T extends Serializable> extends DependentImpl implements ValueStrategy<T>, KlcPropertyContainer {

	private static final long serialVersionUID = -1544693762151102787L;
	
	private StringKlcProperty scope;
    private StringKlcProperty identifier;
    private StringKlcProperty parameterRegex;
    private IntegerKlcProperty index;

    public ExternalValue() {
        scope = new StringKlcProperty("Scope", "Name of the scope.", false, null);
        identifier = new StringKlcProperty("Identifier", "Name of the identifier", false, null);
        parameterRegex = new StringKlcProperty("Parameter Regex", "Regular Expression of the parameter. If empty (completely empty! no spaces/tabs!) is ignored",
                false, null);
        index = new IntegerKlcProperty("Index", "Index of the found values.", false, 0);
    }

    protected T getExternalValue() {

        ExternalValueContainer container = ExternalValueContainer.getInstance();

        String scope = this.scope.getValue();
        String identifier = this.identifier.getValue();
        String parameterRegex = this.parameterRegex.getValue();
        int index = this.index.getValue();

        T value = container.get(scope, identifier, parameterRegex, getType(), index);

        return value;
    }

    @Override
    public KlcWritableProperty<?>[] getProperties() {
        return new KlcWritableProperty[]{scope, identifier, parameterRegex, index};
    }

    protected abstract Class<T> getType();

}
