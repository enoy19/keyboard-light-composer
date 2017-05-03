package org.enoy.klc.common.properties;

import org.enoy.klc.common.Activatable;
import org.enoy.klc.common.Deletable;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.updatables.Dependent;
import org.enoy.klc.common.updatables.Updatable;

public class KlcReadOnlyPropertyValue<T> implements Deletable, Dependent {

	private Class<T> propertyValueType;
	protected T value;
	protected ValueStrategy<T> valueStrategy;
	protected Activatable dependency;

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
		if (this.valueStrategy != null) {
			if(this.valueStrategy instanceof Updatable){
				((Updatable) this.valueStrategy).unregisterUpdatable();
			}
			if(this.valueStrategy instanceof Dependent){
				((Dependent) this.valueStrategy).setDependency(null);
			}
		}
		this.valueStrategy = valueStrategy;
		updateValueStrategyDependency();
		
		if (valueStrategy != null && valueStrategy instanceof Updatable) {
			((Updatable) valueStrategy).registerUpdatable();
		}
	}

	public Class<T> getPropertyValueType() {
		return propertyValueType;
	}

	@Override
	public void delete() {
		if (valueStrategy != null && valueStrategy instanceof Deletable) {
			((Deletable) valueStrategy).delete();
		}
	}

	@Override
	public Activatable getDependency() {
		return dependency;
	}

	@Override
	public void setDependency(Activatable dependency) {
		this.dependency = dependency;
		updateValueStrategyDependency();
	}

	private void updateValueStrategyDependency() {
		if(valueStrategy instanceof Dependent){
			((Dependent) valueStrategy).setDependency(getDependency());
		}
	}

}
