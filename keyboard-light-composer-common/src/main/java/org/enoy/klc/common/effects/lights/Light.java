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
		return toUnsignedByte(getRed());
	}

	public byte getGreenByte() {
		return toUnsignedByte(getGreen());
	}

	public byte getBlueByte() {
		return toUnsignedByte(getBlue());
	}

	public byte getAlphaByte() {
		return toUnsignedByte(getAlpha());
	}

	private byte toUnsignedByte(float value) {
		return (byte) ((0xFF) & (short)(value * 255));
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		sb.append(red+", ");
		sb.append(green+", ");
		sb.append(blue+", ");
		sb.append(alpha+" ]");
		return sb.toString();
	}

	public void applyOpacity(float opacity) {
		setAlpha(getAlpha() * opacity);
	}
	
}
