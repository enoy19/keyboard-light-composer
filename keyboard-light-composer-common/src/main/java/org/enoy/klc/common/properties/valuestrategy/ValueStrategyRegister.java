package org.enoy.klc.common.properties.valuestrategy;

import org.enoy.klc.common.Register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueStrategyRegister extends Register<ValueStrategyFactory> {

	private static ValueStrategyRegister instance;
	private final Map<String, List<ValueStrategyFactory>> FACTORIES = new HashMap<>();

	private ValueStrategyRegister() {

	}

	public List<ValueStrategyFactory> getValueStrategiesFor(Class<?> valueType) {
		List<ValueStrategyFactory> list = getList(valueType);
		return new ArrayList<>(list);
	}

	private List<ValueStrategyFactory> getList(Class<?> valueType) {

		String key = valueType.getName();
		List<ValueStrategyFactory> list = FACTORIES.get(key);

		if (list == null) {
			FACTORIES.put(key, list = new ArrayList<>());
			getRegisteredStream()//
					.filter(factory -> {
						Class<?> factoryGenericType = factory.getFactoryGenericType();
						return factoryGenericType != null && factoryGenericType.equals(valueType);
					}).forEach(list::add);
		}

		return list;
	}

	public static ValueStrategyRegister getInstance() {
		return instance == null ? instance = new ValueStrategyRegister() : instance;
	}

}
