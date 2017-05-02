package org.enoy.klc.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Register<T> {

	private Set<T> register;

	public Register() {
		register = new HashSet<>();
	}

	public Iterable<T> getRegisteredObjects() {
		return register;
	}

	public List<T> getRegisteredObjectsAsList() {
		return new ArrayList<>(register);
	}

	public Stream<T> getRegisteredStream() {
		return register.stream();
	}
	
	public Stream<T> getRegisteredParallelStream(){
		return register.parallelStream();
	}

	public void register(T object) {
		register.add(object);
	}

	public void unregister(T object) {
		register.remove(object);
	}

}
