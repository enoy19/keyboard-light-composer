package org.enoy.klc.control.updater;

import org.enoy.klc.common.Activatable;
import org.enoy.klc.common.properties.KlcPropertyContainer;
import org.enoy.klc.common.properties.KlcWritableProperty;
import org.enoy.klc.common.updatables.Dependent;
import org.enoy.klc.common.updatables.Updatable;
import org.enoy.klc.common.updatables.UpdatableRegister;
import org.enoy.klc.control.StopPauseLoop;

public class Updater extends StopPauseLoop {

	private UpdatableRegister updatableRegister;

	public void setUpdatableRegister(UpdatableRegister updatableRegister) {
		this.updatableRegister = updatableRegister;
	}

	@Override
	public void executeLoop(double delta) {
		synchronized (updatableRegister) {
			updatableRegister.getRegisteredParallelStream().filter(u -> u.isDirty()).filter(u -> {
				if (u instanceof Dependent) {
					Activatable dependency = ((Dependent) u).getDependency();
					boolean active = dependency == null || dependency.isActive();
					return active;
				}
				return true;
			}).forEach(u -> u.updateAndClean(delta));
		}
	}

	@Override
	public void setPaused(boolean paused) {
		super.setPaused(paused);
		if (!paused) {
			setDependencies();
		}
	}

	private void setDependencies() {
		updatableRegister.getRegisteredParallelStream().forEach(u -> {
			inheritDependencies(u);
		});
	}

	private void inheritDependencies(Updatable u) {
		if (u instanceof KlcPropertyContainer) {
			Activatable dependency;

			if (u instanceof Activatable) {
				dependency = (Activatable) u;
			} else if (u instanceof Dependent) {
				dependency = ((Dependent) u).getDependency();
			} else {
				return;
			}

			if (dependency != null) {
				KlcPropertyContainer container = (KlcPropertyContainer) u;
				KlcWritableProperty<?>[] properties = container.getProperties();
				for (int i = 0; i < properties.length; i++) {
					KlcWritableProperty<?> property = properties[i];
					property.setDependency(dependency);
				}
			}
		}
	}

}
