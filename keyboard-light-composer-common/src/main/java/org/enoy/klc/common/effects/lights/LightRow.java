package org.enoy.klc.common.effects.lights;

public class LightRow {

	private final int width;
	private Light[] lights;

	public LightRow(int width) {
		this.width = width;
		lights = new Light[width];

		for (int i = 0; i < width; i++) {
			lights[i] = new Light();
		}
	}

	public Light[] getLights() {
		return lights;
	}

	public Light getLight(int index) {
		return lights[index];
	}

	public void setLight(int index, float red, float green, float blue,
			float alpha) {
		Light light = getLight(index);
		light.setRed(red);
		light.setGreen(green);
		light.setBlue(blue);
		light.setAlpha(alpha);
	}

	public void setLight(int index, float red, float green, float blue) {
		setLight(index, red, green, blue, 1);
	}

	public void setLight(int index, Light light) {
		setLight(index, light.getRed(), light.getGreen(), light.getBlue(), light.getAlpha());
	}
	
	public int getWidth() {
		return width;
	}

	public void applyOpacity(float opacity) {
		for (int i = 0; i < lights.length; i++) {
			lights[i].applyOpacity(opacity);
		}
	}

}