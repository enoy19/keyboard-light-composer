package org.enoy.klc.control.registerer;

import java.util.Set;

import org.enoy.klc.common.Register;
import org.reflections.Reflections;

public class Registerer {

	public static <T> void registerObjects(Class<T> clazz, Register<T> register)
			throws ReflectiveOperationException {

		Set<Class<? extends T>> classes = getSubTypesOf(clazz);

		for (Class<? extends T> objectClass : classes) {
			T object = objectClass.newInstance();
			register.register(object);
		}

	}

	public static <T> void registerClasses(Class<T> clazz,
			Register<Class<? extends T>> register) {

		Set<Class<? extends T>> classes = getSubTypesOf(clazz);

		for (Class<? extends T> objectClass : classes) {
			register.register(objectClass);
		}

	}

	private static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> clazz) {
		Reflections reflections = new Reflections("");

		Set<Class<? extends T>> classes = reflections.getSubTypesOf(clazz);
		return classes;
	}

}
