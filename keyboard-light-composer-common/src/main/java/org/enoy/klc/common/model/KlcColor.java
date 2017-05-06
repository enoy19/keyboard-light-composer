package org.enoy.klc.common.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

// TODO: extends Color?
public class KlcColor implements Externalizable {
	
	private static final long serialVersionUID = 6077172583776199343L;

	private Color color;

	public KlcColor() {
		color = new Color(0, 0, 0, 1);
	}

	public KlcColor(double red, double green, double blue, double alpha) {
		red = zeroToOne(red);
		green = zeroToOne(green);
		blue = zeroToOne(blue);
		alpha = zeroToOne(alpha);
		
		color = new Color(red, green, blue, alpha);
	}

	private double zeroToOne(double value) {
		return value > 1 ? 1 : value < 0 ? 0 : value;
	}

	public KlcColor(float red, float green, float blue, float alpha) {
		this((double) red, (double) green, (double) blue, (double) alpha);
	}

	public KlcColor(Color color) {
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(color.getRed());
		out.writeObject(color.getGreen());
		out.writeObject(color.getBlue());
		out.writeObject(color.getOpacity());
		out.flush();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		double red = (double) in.readObject();
		double green = (double) in.readObject();
		double blue = (double) in.readObject();
		double opacity = (double) in.readObject();
		color = new Color(red, green, blue, opacity);
	}

}
