package org.enoy.klc.common.effects;

public class EffectFactory {
	
	private String name;
	private String group;
	private Class<? extends Effect> effectClass;

	public EffectFactory(String name, String group, Class<? extends Effect> effectClass) {
		this.name = name;
		this.group = group;
		this.effectClass = effectClass;
	}

	public Effect createEffect() {
		try {
			return effectClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

}
