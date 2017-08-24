package org.enoy.klc.control.registerer;

import org.enoy.klc.common.Register;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Set;

public class Registerer {

	public static <T> void registerObjects(Class<T> clazz, Register<T> register) throws ReflectiveOperationException {

		Set<Class<? extends T>> classes = getSubTypesOf(clazz);

		for (Class<? extends T> objectClass : classes) {
			if (!isAbstract(objectClass)) {
				T object = objectClass.newInstance();
				register.register(object);
			}
		}

	}

	public static <SEARCH, T> void registerParsed(Class<SEARCH> searchClazz, RegisterParser<SEARCH, T> parser,
			Register<T> register) {

		Set<Class<? extends SEARCH>> classes = getSubTypesOf(searchClazz);

		for (Class<? extends SEARCH> objectClass : classes) {
			if (!isAbstract(objectClass)) {
				T parsed = parser.getParsed(objectClass);
				register.register(parsed);
			}
		}

	}

	private static boolean isAbstract(Class<?> objectClass) {
		return Modifier.isAbstract(objectClass.getModifiers());
	}

	private static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> clazz) {
		Reflections reflections = new Reflections("");

		Set<Class<? extends T>> classes = reflections.getSubTypesOf(clazz);
		return classes;
	}

	public static interface RegisterParser<SOURCE, TARGET> {

		public TARGET getParsed(Class<? extends SOURCE> source);

	}

}
