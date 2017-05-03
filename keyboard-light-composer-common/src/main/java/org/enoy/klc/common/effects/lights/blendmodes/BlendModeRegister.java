package org.enoy.klc.common.effects.lights.blendmodes;

import org.enoy.klc.common.Register;

public class BlendModeRegister extends Register<BlendModeFactory> {

	private static BlendModeRegister instance;

	private BlendModeRegister() {
	}

	public static BlendModeRegister getInstance() {
		return instance == null ? instance = new BlendModeRegister() : instance;
	}

}
