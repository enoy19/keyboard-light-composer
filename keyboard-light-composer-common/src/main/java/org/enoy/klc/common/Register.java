package org.enoy.klc.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Register<T> {

	private Set<T> register;

	public Register() {
		register = new HashSet<>();
	}

	public Iterable<T> getRegisteredObjects() {
		return register;
	}
	
	public List<T> getRegisteredObjectsAsList(){
		return new ArrayList<>(register);
	}
	
	public void register(T object) {
		register.add(object);
	}

	public void unregisterDevice(T object) {
		register.remove(object);
	}

}
