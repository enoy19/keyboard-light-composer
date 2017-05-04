package org.enoy.klc.common.effects.lights.scalemodes;

import org.enoy.klc.common.Register;

public class ScaleModeRegister extends Register<ScaleModeFactory> {

	private static ScaleModeRegister instance;

	private ScaleModeRegister() {
	}

	public static ScaleModeRegister getInstance() {
		return instance == null ? instance = new ScaleModeRegister() : instance;
	}

}
