package org.enoy.klc.common.effects.lights;

import javafx.scene.paint.Color;

public class Light {

	private float red;
	private float green;
	private float blue;
	private float alpha;

	public Light() {
	}

	public Light(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public byte getRedByte() {
		return floatZeroToOneToByte(getRed());
	}

	public byte getGreenByte() {
		return floatZeroToOneToByte(getGreen());
	}

	public byte getBlueByte() {
		return floatZeroToOneToByte(getBlue());
	}

	public byte getAlphaByte() {
		return floatZeroToOneToByte(getAlpha());
	}

	private byte floatZeroToOneToByte(float value) {
		return (byte) (value * 255f - 128f);
	}

	public Color getColor() {
		return new Color(getRed(), getGreen(), getBlue(), getAlpha());
	}

	public void set(Light other) {
		this.setRed(other.getRed());
		this.setGreen(other.getGreen());
		this.setBlue(other.getBlue());
		this.setAlpha(other.getAlpha());
	}

}
