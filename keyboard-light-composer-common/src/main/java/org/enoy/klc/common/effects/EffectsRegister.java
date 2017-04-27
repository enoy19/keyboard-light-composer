package org.enoy.klc.common.effects;

import org.enoy.klc.common.Register;

public class EffectsRegister extends Register<Class<? extends Effect>> {

	private static EffectsRegister instance;

	private EffectsRegister() {

	}

	// TODO: effect definition? Effect annotation with name and description and
	// author?

	public static EffectsRegister getInstance() {
		return instance == null ? instance = new EffectsRegister() : instance;
	}

}
