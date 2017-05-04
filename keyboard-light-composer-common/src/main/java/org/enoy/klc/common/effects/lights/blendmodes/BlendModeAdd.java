package org.enoy.klc.common.effects.lights.blendmodes;

import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.factories.Name;

// TODO: move this to controller
@Name("Add")
public class BlendModeAdd implements BlendMode {

	@Override
	public Light blend(Light lightA, Light lightB) {
		
		float alpha = lightB.getAlpha();
		
		float red = lightA.getRed() + lightB.getRed() * alpha;
		float green = lightA.getGreen() + lightB.getGreen() * alpha;
		float blue = lightA.getBlue() + lightB.getBlue() * alpha;

		red = Math.min(red, 1);
		green = Math.min(green, 1);
		blue = Math.min(blue, 1);

		return new Light(red, green, blue, 1);
	}

}
