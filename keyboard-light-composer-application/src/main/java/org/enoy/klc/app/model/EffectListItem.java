package org.enoy.klc.app.model;

import org.enoy.klc.common.effects.Effect;

public class EffectListItem {

	private String name;
	private Class<? extends Effect> effectClass;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<? extends Effect> getEffectClass() {
		return effectClass;
	}
	public void setEffectClass(Class<? extends Effect> effectClass) {
		this.effectClass = effectClass;
	}
	
}
