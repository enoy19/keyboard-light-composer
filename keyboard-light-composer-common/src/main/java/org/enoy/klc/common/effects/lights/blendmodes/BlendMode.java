package org.enoy.klc.common.effects.lights.blendmodes;

import java.io.Serializable;

import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.effects.lights.LightMatrix;

public interface BlendMode extends Serializable {

	/**
	 * @param lightA below
	 * @param lightB on top
	 * @return
	 */
	public Light blend(Light lightA, Light lightB);

	public default void blend(LightMatrix lightMatrixA,
			LightMatrix lightMatrixB) {

		int width = Math.min(lightMatrixA.getWidth(), lightMatrixB.getWidth());
		int height = Math.min(lightMatrixA.getHeight(),
				lightMatrixB.getHeight());

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Light lightA = lightMatrixA.getLight(i, j);
				Light lightB = lightMatrixB.getLight(i, j);

				Light resultLight = blend(lightA, lightB);
				lightMatrixA.setLight(i, j, resultLight);
			}
		}

	}

}
