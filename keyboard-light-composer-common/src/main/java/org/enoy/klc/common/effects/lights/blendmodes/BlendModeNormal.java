package org.enoy.klc.common.effects.lights.blendmodes;

import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.factories.Name;

// TODO: move this to controller & implement
@Name("Normal")
public class BlendModeNormal implements BlendMode{

	@Override
	public Light blend(Light lightA, Light lightB) {
		return null;
	}

}
