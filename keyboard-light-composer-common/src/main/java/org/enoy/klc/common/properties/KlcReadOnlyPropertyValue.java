package org.enoy.klc.common.properties;

import org.enoy.klc.common.Deletable;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.updatables.Updatable;

public class KlcReadOnlyPropertyValue<T> implements Deletable {

	private Class<T> propertyValueType;
	protected T value;
	protected ValueStrategy<T> valueStrategy;

	public KlcReadOnlyPropertyValue(Class<T> propertyValueType, T value, ValueStrategy<T> valueStrategy) {
		this.propertyValueType = propertyValueType;
		this.value = value;
		this.valueStrategy = valueStrategy;
	}

	public KlcReadOnlyPropertyValue(Class<T> propertyValueType, T value) {
		this(propertyValueType, value, null);
	}

	public KlcReadOnlyPropertyValue(Class<T> propertyValueType) {
		this(propertyValueType, null);
	}

	public T getValue() {
		if (valueStrategy != null) {
			return valueStrategy.getValue();
		}
		return value;
	}

	public ValueStrategy<T> getValueStrategy() {
		return valueStrategy;
	}

	protected void setValue(T value) {
		this.value = value;
	}

	protected void setValueStrategy(ValueStrategy<T> valueStrategy) {
		// TODO: when loading serializable must register!
		// TODO: when deleted!
		if (this.valueStrategy != null && this.valueStrategy instanceof Updatable) {
			((Updatable) this.valueStrategy).unregisterUpdatable();
		}
		if (valueStrategy != null && valueStrategy instanceof Updatable) {
			((Updatable) valueStrategy).registerUpdatable();
		}
		this.valueStrategy = valueStrategy;
	}

	public Class<T> getPropertyValueType() {
		return propertyValueType;
	}

	@Override
	public void delete() {
		if(valueStrategy != null && valueStrategy instanceof Deletable){
			((Deletable)valueStrategy).delete();
		}
	}

}
