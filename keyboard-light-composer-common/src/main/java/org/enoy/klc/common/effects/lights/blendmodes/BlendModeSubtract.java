package org.enoy.klc.common.effects.lights.blendmodes;

import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.factories.Name;

// TODO: move this to controller
@Name("Subtract")
public class BlendModeSubtract implements BlendMode {

	private static final long serialVersionUID = 6744361291696184905L;

	@Override
	public Light blend(Light lightA, Light lightB) {

		float alpha = lightB.getAlpha();

		float red = lightA.getRed() - lightB.getRed() * alpha;
		float green = lightA.getGreen() - lightB.getGreen() * alpha;
		float blue = lightA.getBlue() - lightB.getBlue() * alpha;

		red = Math.max(red, 0);
		green = Math.max(green, 0);
		blue = Math.max(blue, 0);

		return new Light(red, green, blue, 1);
	}

}
