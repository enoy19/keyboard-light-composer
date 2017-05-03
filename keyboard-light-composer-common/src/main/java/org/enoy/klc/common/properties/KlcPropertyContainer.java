package org.enoy.klc.common.properties;

import org.enoy.klc.common.Deletable;

public interface KlcPropertyContainer extends Deletable {
	
	public KlcWritableProperty<?>[] getProperties();
	
	@Override
	default void delete() {
		KlcWritableProperty<?>[] properties = getProperties();
		if(properties != null && properties.length > 0){
			synchronized (properties) {
				for (KlcWritableProperty<?> klcWritableProperty : properties) {
					klcWritableProperty.delete();
				}
			}
		}
	}

}
