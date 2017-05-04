package org.enoy.klc.common.effects.lights.blendmodes;

import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.factories.Name;

// TODO: move this to controller & implement
@Name("Normal")
public class BlendModeNormal implements BlendMode{

	@Override
	public Light blend(Light lightA, Light lightB) {
		
		float invertedAlpha = 1 - lightB.getAlpha();
		float red = lightB.getRed() + lightA.getRed() * invertedAlpha;
		float green = lightB.getGreen() + lightA.getGreen() * invertedAlpha;
		float blue = lightB.getBlue() + lightA.getBlue() * invertedAlpha;
		
		return new Light(red, green, blue, 1);
	}

}
