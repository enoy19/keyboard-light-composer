package org.enoy.klc.common.effects.lights.blendmodes;

import org.enoy.klc.common.effects.lights.Light;

// TODO: move this to controller
public class BlendModeAdd implements BlendMode {

	private static BlendModeAdd instance;

	private BlendModeAdd() {

	}

	@Override
	public Light blend(Light lightA, Light lightB) {
		float red = lightA.getRed() + lightB.getRed();
		float green = lightA.getGreen() + lightB.getGreen();
		float blue = lightA.getBlue() + lightB.getBlue();
		float alpha = lightA.getAlpha() + lightB.getAlpha();

		red = Math.min(red, 1);
		green = Math.min(green, 1);
		blue = Math.min(blue, 1);
		alpha = Math.min(alpha, 1);

		return new Light(red, green, blue, alpha);
	}

	public static BlendModeAdd getInstance() {
		return instance == null ? instance = new BlendModeAdd() : instance;
	}

}
