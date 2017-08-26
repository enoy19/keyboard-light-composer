package org.enoy.klc.control.external;

import org.enoy.klc.control.external.ExternalValue.ExternalValueDataType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ExternalValueContainer {

	private static ExternalValueContainer instance;
	private Map<ExternalValueNamespace, Set<ExternalValue<?>>> values;

	private ExternalValueContainer() {
		values = new ConcurrentHashMap<>();
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String scope, String identifier, String parameterRegex, Class<T> type, int index) {
		ExternalValueNamespace namespace = getExternalValueNamespace(scope, identifier);

		Set<ExternalValue<?>> values = getValues(namespace);

		List<ExternalValue<?>> matchingValues;
		synchronized (values) {
			matchingValues =
					values.stream()
							.filter(v -> {
								if (parameterRegex != null && !parameterRegex.isEmpty())
									if (v.getParameter() != null)
										return v.getParameter().matches(parameterRegex);
									else
										return false;
								else
									return true;
							})
							.sorted(Comparator.comparing(ExternalValue::getParameter))
							.collect(Collectors.toList());
		}

		if (matchingValues.size() <= index)
			return null;
		else {
			ExternalValue<?> matchingValue = matchingValues.get(index);
			Class<?> matchingValueType = matchingValue.getDataType().getType();
			if (matchingValueType.isAssignableFrom(type)) {
				return (T) matchingValue.getData();
			} else {
				return null;
			}
		}
	}

	public <T> T get(String scope, String identifier, String parameterRegex, Class<T> type) {
		return get(scope, identifier, parameterRegex, type, 0);
	}

	void put(String scope, String identifier, String parameter, String dataTypeIdString, String dataString) {

		int dataTypeId;

		try {
			dataTypeId = Integer.parseInt(dataTypeIdString);
		} catch (NumberFormatException e) {
			// do nothing
			return;
		}

		ExternalValueDataType dataType = ExternalValueDataType.getById(dataTypeId);
		Object data;

		try {
			data = dataType.convert(dataString);
		} catch (Exception e) {
			// print but ignore
			System.err.println("\nFailed to convert value: \"" + dataString + "\" DataType: " + dataType);
			e.printStackTrace();
			return;
		}

		put(scope, identifier, parameter, dataTypeId, data);

	}

	void put(String scope, String identifier, String parameter, int dataTypeId, Object data) {

		ExternalValueNamespace namespace = getExternalValueNamespace(scope, identifier);

		Set<ExternalValue<?>> values = getValues(namespace);

		ExternalValue<?> value = getValue(parameter, dataTypeId, data);

		synchronized (values) {
			values.remove(value);
			values.add(value);
		}

		//TODO: update val strategies?

	}

	private ExternalValueNamespace getExternalValueNamespace(String scope, String identifier) {
		return new ExternalValueNamespace(scope, identifier);
	}

	private ExternalValue<?> getValue(String parameter, int dataTypeId, Object data) {

		ExternalValue<?> value;

		ExternalValueDataType dataType = ExternalValueDataType.getById(dataTypeId);

		if (dataType == null)
			throw new RuntimeException("no datatype for id: " + dataTypeId);

		switch (dataType) {
			case BOOLEAN:
				value = new ExternalValueBoolean((Boolean) data);
				break;
			case FLOAT:
				value = new ExternalValueFloat((Float) data);
				break;
			case LONG:
				value = new ExternalValueLong((Long) data);
				break;
			case STRING:
				value = new ExternalValueString((String) data);
				break;
			default:
				throw new Error("datatype is not registered in external value container");
		}

		value.setParameter(parameter);

		return value;

	}

	private Set<ExternalValue<?>> getValues(ExternalValueNamespace namespace) {
		values.computeIfAbsent(namespace, ns -> new HashSet<>());
		Set<ExternalValue<?>> values = this.values.get(namespace);

		return values;
	}

	public static ExternalValueContainer getInstance() {
		return instance == null ? (instance = new ExternalValueContainer()) : instance;
	}

	public Map<ExternalValueNamespace, Set<ExternalValue<?>>> getAll() {

		synchronized (this.values)
		{
			Map<ExternalValueNamespace, Set<ExternalValue<?>>> values = new HashMap<>(this.values);
		}

		return values;

	}
}
