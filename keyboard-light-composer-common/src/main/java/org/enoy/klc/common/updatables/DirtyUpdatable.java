package org.enoy.klc.common.updatables;

public interface DirtyUpdatable extends Updatable {

	@Override
	default boolean isDirty() {
		return true;
	}

	@Override
	public default void setDirty(boolean dirty) {
	};

}
