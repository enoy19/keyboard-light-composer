package org.enoy.klc.effects.standard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.enoy.klc.common.Resettable;
import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.effects.lights.Light;
import org.enoy.klc.common.effects.lights.LightMatrix;
import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeAdd;
import org.enoy.klc.common.factories.Group;
import org.enoy.klc.common.factories.Name;
import org.enoy.klc.common.model.KlcColor;
import org.enoy.klc.common.properties.KlcProperty;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.properties.primitive.FloatKlcProperty;
import org.enoy.klc.common.properties.primitive.IntegerKlcProperty;
import org.enoy.klc.common.updatables.DependentImpl;
import org.enoy.klc.common.updatables.DirtyUpdatable;

import javafx.scene.paint.Color;

@Name("Fire")
@Group("Standard Effects")
public class FireEffect extends DependentImpl implements Effect, KlcPropertyContainer, DirtyUpdatable, Resettable {

	private static final long serialVersionUID = 9160815839275855707L;
	private static final Random RANDOM = new Random();

	private BlendMode blendMode = new BlendModeAdd();
	
	private KlcProperty<KlcColor> color;
	private FloatKlcProperty speed;
	private FloatKlcProperty spawnRate;
	private IntegerKlcProperty width;
	private IntegerKlcProperty height;

	private float counter;
	private transient List<Particle> particles;

	private transient volatile LightMatrix currentLightMatrix;

	public FireEffect() {
		this.color = new KlcProperty<KlcColor>(KlcColor.class, "Color", "The color of the line", true, new KlcColor());
		this.speed = new FloatKlcProperty("Speed", "Movement speed of the particles", true, 5f);
		this.spawnRate = new FloatKlcProperty("Spawnrate", "Rate in which the particles spawn", true, 100f);
		this.width = new IntegerKlcProperty("Width", "Width of this effects grid", true, 21);
		this.height = new IntegerKlcProperty("Height", "Height of this effects grid", true, 6);
	}

	@Override
	public LightMatrix getColorMatrix() {
		return currentLightMatrix == null ? new LightMatrix(1, 1) : currentLightMatrix;
	}

	@Override
	public String getName() {
		return "Fire";
	}

	@Override
	public KlcWritableProperty<?>[] getProperties() {
		return new KlcWritableProperty[] { color, speed, spawnRate, width, height };
	}

	@Override
	public void delete() {
		KlcPropertyContainer.super.delete();
		DirtyUpdatable.super.delete();
	}

	@Override
	public void update(double delta) {
		float spawnRate = this.spawnRate.getValue();
		float speed = this.speed.getValue();

		counter += delta;
		if (counter > 0 && counter > 1 / spawnRate) {
			spawn(speed);
			counter = 0;
		}

		particles.parallelStream().forEach(p -> p.update(delta));

		render();

		killOutsideParticles();
	}

	private void render() {
		Color color = this.color.getValue().getColor();
		int width = this.width.getValue();
		int height = this.height.getValue();

		LightMatrix lm = new LightMatrix(width, height);

		particles//
				.stream()//
				.forEach(p -> putParticleInMatrix(p, lm, color));

		currentLightMatrix = lm;
	}

	private void putParticleInMatrix(Particle particle, LightMatrix matrix, Color color) {
		int x = Math.abs((int) particle.x);
		int y = Math.abs((int) particle.y);

		if (x < 0 || y < 0 || x > matrix.getWidth() - 1 || y > matrix.getHeight() - 1) {
			return;
		}

		Light light = new Light(color);
		light = blendMode.blend(matrix.getLight(y, x), light);
		matrix.setLight(y, x, light);
	}

	private void killOutsideParticles() {
		List<Particle> deadParticles = particles.stream()//
				.filter(p -> p.y < 0)//
				.collect(Collectors.toList());

		particles.removeAll(deadParticles);
	}

	private void spawn(float speed) {
		float x = RANDOM.nextFloat() * (float) width.getValue();
		particles.add(new Particle(x, speed));
	}

	@Override
	public void reset() {
		particles = new ArrayList<>();
	}

	private class Particle implements DirtyUpdatable {

		private float x;
		private float y;
		private float speed;

		public Particle(float x, float speed) {
			this.x = x;
			this.speed = speed;
			this.y = height.getValue();
		}

		@Override
		public void update(double delta) {
			y -= speed * delta;
		}

	}

}
