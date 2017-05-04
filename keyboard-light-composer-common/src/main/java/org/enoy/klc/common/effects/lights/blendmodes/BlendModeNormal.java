package org.enoy.klc.common.effects.lights.blendmodes;

import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.factories.Name;

// TODO: move this to controller & implement
@Name("Normal")
public class BlendModeNormal implements BlendMode{

	@Override
	public Light blend(Light lightA, Light lightB) {
		
		float alpha = lightB.getAlpha();
		float invertedAlpha = 1 - alpha;
		float red = lightB.getRed() * alpha + lightA.getRed() * invertedAlpha;
		float green = lightB.getGreen() * alpha + lightA.getGreen() * invertedAlpha;
		float blue = lightB.getBlue() * alpha + lightA.getBlue() * invertedAlpha;
		
		return new Light(red, green, blue, 1);
	}

}
