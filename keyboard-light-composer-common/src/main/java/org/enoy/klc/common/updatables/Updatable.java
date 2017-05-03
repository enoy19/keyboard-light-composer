package org.enoy.klc.common.updatables;

import org.enoy.klc.common.Deletable;

public interface Updatable extends Deletable {

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
		UpdatableRegister.getInstance().register(this);
	}

	public default void unregisterUpdatable() {
		UpdatableRegister.getInstance().unregister(this);
	}
	
	@Override
	default void delete() {
		unregisterUpdatable();
	}

}
