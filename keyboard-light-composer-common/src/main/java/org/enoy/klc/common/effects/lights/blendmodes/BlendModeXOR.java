package org.enoy.klc.common.effects.lights.blendmodes;

import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.factories.Name;

// TODO: move this to controller
@Name("XOR")
public class BlendModeXOR implements BlendMode {

	private static final long serialVersionUID = 6744361291696184905L;

	@Override
	public Light blend(Light lightA, Light lightB) {

		float alpha = lightB.getAlpha();

		float bRed = lightB.getRed() * alpha;
		float bGreen = lightB.getGreen() * alpha;
		float bBlue = lightB.getBlue() * alpha;
		
		int intBRed = (int) (bRed * 255);
		int intBGreen = (int) (bGreen * 255);
		int intBBlue = (int) (bBlue * 255);
		
		int intARed = (int) (lightA.getRed() * 255);
		int intAGreen = (int) (lightA.getGreen() * 255);
		int intABlue = (int) (lightA.getBlue() * 255);

		float red = (float)(intARed ^ intBRed) / 255f;
		float green = (float)(intAGreen ^ intBGreen) / 255f;
		float blue = (float)(intABlue ^ intBBlue) / 255f;

		return new Light(red, green, blue, 1);
	}

}
