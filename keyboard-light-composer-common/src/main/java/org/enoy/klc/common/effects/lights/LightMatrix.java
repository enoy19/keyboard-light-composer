package org.enoy.klc.common.effects.lights;

public class LightMatrix {

	private final int width;
	private final int height;
	private LightRow[] rows;

	public LightMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		rows = new LightRow[height];

		for (int i = 0; i < height; i++) {
			rows[i] = new LightRow(width);
		}
	}

	public LightRow[] getLightRows() {
		return rows;
	}

	public LightRow getLightRow(int index) {
		return rows[index];
	}

	public Light getLight(int row, int index) {
		return getLightRow(row).getLight(index);
	}

	public void setLight(int row, int index, float red, float green, float blue,
			float alpha) {
		getLightRow(row).setLight(index, red, green, blue, alpha);
	}

	public void setLight(int row, int index, double red, double green,
			double blue, double alpha) {
		setLight(row, index, (float) red, (float) green, (float) blue,
				(float) alpha);
	}

	public void setLight(int row, int index, float red, float green,
			float blue) {
		getLightRow(row).setLight(index, red, green, blue, 1);
	}

	public void setLight(int row, int index, Light light) {
		getLightRow(row).setLight(index, light);
	}

	public void setLightRow(int index, LightRow lightRow) {
		LightRow row = getLightRow(index);
		int min = Math.min(row.getWidth(), lightRow.getWidth());

		for (int i = 0; i < min; i++) {
			row.setLight(i, lightRow.getLight(i));
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public LightMatrix getCopy() {
		LightMatrix copy = new LightMatrix(width, height);
		setLightMatrix(copy);
		return copy;
	}

	protected void setLightMatrix(LightMatrix other) {
		int min = Math.min(width, other.width);

		for (int i = 0; i < min; i++) {
			other.setLightRow(i, getLightRow(i));
		}
	}

}