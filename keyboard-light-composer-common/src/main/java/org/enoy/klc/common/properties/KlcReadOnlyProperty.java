package org.enoy.klc.common.properties;

import org.enoy.klc.common.Activatable;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;

import java.io.Serializable;

public class KlcReadOnlyProperty<T extends Serializable> implements KlcPropertyBase<T> {

	private static final long serialVersionUID = -2806512082375290750L;

	private Class<T> propertyType;
	protected String name;
	protected String description;
	private final boolean valueStrategyAllowed;
	protected KlcPropertyValue<T> propertyValue;
	protected T defaultValue;
	private Activatable dependency;

	public KlcReadOnlyProperty(Class<T> propertyType, String name, String description, boolean valueStrategyAllowed,
			T defaultValue) {

		this.propertyType = propertyType;
		this.name = name;
		this.description = description;
		this.valueStrategyAllowed = valueStrategyAllowed;
		this.propertyValue = new KlcPropertyValue<T>(propertyType, defaultValue, null);
		this.defaultValue = defaultValue;
	}


	@Override
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean isValueStrategyAllowed() {
		return valueStrategyAllowed;
	}

	// protected void setValueStrategyAllowed(boolean valueStrategyAllowed) {
	// this.valueStrategyAllowed = valueStrategyAllowed;
	// }

	@Override
	public T getValue() {
		T value = getPropertyValue().getValue();
		if (value == null) {
			value = getDefaultValue();
		}
		return value;
	}

	protected void setValue(T value) {
		propertyValue.setValue(value);
	}

	@Override
	public KlcReadOnlyPropertyValue<T> getPropertyValue() {
		return propertyValue;
	}

	@Override
	public T getDefaultValue() {
		return defaultValue;
	}

	protected void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	protected void setValueStrategy(ValueStrategy<T> valueStrategy) {
		propertyValue.setValueStrategy(valueStrategy);
	}

	@Override
	public Class<T> getPropertyType() {
		return propertyType;
	}

	public boolean isValueStrategyPresent() {
		return propertyValue.getValueStrategy() != null;
	}

	@Override
	public void delete() {
		propertyValue.delete();
	}

	@Override
	public Activatable getDependency() {
		return dependency;
	}

	@Override
	public void setDependency(Activatable dependency) {
		this.dependency = dependency;
		this.propertyValue.setDependency(dependency);
	}

}
