package org.enoy.klc.common.updatables;

public interface Updatable {

	public boolean isDirty();
	public void setDirty(boolean dirty);
	public void update(double delta);

	public default void updateAndClean(double delta) {
		update(delta);
		clean();
	}

	public default void markAsDirty() {
		setDirty(true);
	}

	public default void clean() {
		setDirty(false);
	}

	public default void registerUpdatable() {
		UpdatableRegister.getInstance().registerUpdatable(this);
	}

	public default void unregisterUpdatable() {
		UpdatableRegister.getInstance().unregisterUpdatable(this);
	}

}
