package org.enoy.klc.control.utils;

import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;

public class LayerBaseUtil {

	public static String getName(LayerBase layerBase) {

		if (layerBase instanceof EffectLayer) {
			return ((EffectLayer) layerBase).getEffectLayerInformation().getName().getValue();
		} else if (layerBase instanceof EffectGroupLayer) {
			return ((EffectGroupLayer) layerBase).getEffectLayerInformation().getName().getValue();
		}

		throw new UnsupportedOperationException("the " + LayerBase.class.getSimpleName() + " subtype is not supported: "
				+ layerBase.getClass().getName());
	}

}
