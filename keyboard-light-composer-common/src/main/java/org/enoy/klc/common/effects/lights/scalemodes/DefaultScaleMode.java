package org.enoy.klc.common.effects.lights.scalemodes;

import org.enoy.klc.common.effects.lights.LightMatrix;

// TODO: move this to controller
public class DefaultScaleMode implements ScaleMode {

	private static DefaultScaleMode instance;

	private DefaultScaleMode() {

	}

	@Override
	public LightMatrix scale(LightMatrix lightMatrix, int targetWidth,
			int targetHeight) {

		int originalWidth = lightMatrix.getWidth();
		int originalHeight = lightMatrix.getHeight();

		if (targetWidth == originalWidth && targetHeight == originalHeight) {
			return lightMatrix;
		}

		double differenceWidth = (double) originalWidth / (double) targetWidth;
		double differenceHeight = (double) originalHeight
				/ (double) targetHeight;

		LightMatrix target = new LightMatrix(targetWidth, targetHeight);

		for (int i = 0; i < targetHeight; i++) {
			int originalRowIndex = (int) Math
					.floor((double) i * differenceHeight);

			for (int j = 0; j < targetWidth; j++) {
				int originalColumnIndex = (int) Math
						.floor((double) j * differenceWidth);
				target.setLight(i, j, lightMatrix.getLight(originalRowIndex,
						originalColumnIndex));
			}
		}

		return target;

	}

	public static DefaultScaleMode getInstance() {
		return instance == null ? instance = new DefaultScaleMode() : instance;
	}

}
