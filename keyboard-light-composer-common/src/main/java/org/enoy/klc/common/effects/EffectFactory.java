package org.enoy.klc.common.effects;

import org.enoy.klc.common.effects.describers.EffectGroup;
import org.enoy.klc.common.effects.describers.EffectName;

public class EffectFactory {

	private String name;
	private String group;
	private Class<? extends Effect> effectClass;

	public EffectFactory(String name, String group,
			Class<? extends Effect> effectClass) {
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

	public static EffectFactory createEffectFactory(Class<? extends Effect> effectClass) {
		String name = getEffectName(effectClass);
		String group = getEffectGroup(effectClass);
		EffectFactory factory = new EffectFactory(name, group, effectClass);
		return factory;
	}

	private static String getEffectName(Class<? extends Effect> effectClass) {
		String effectNameValue;
		EffectName effectName = effectClass.getAnnotation(EffectName.class);

		if (effectName != null && (effectNameValue = effectName.value()) != null && !effectNameValue.trim().isEmpty()) {
			return effectNameValue;
		} else {
			return effectClass.getSimpleName();
		}
	}

	private static String getEffectGroup(Class<? extends Effect> effectClass) {
		EffectGroup effectGroup = effectClass.getAnnotation(EffectGroup.class);
		String effectGroupValue;

		if (effectGroup != null && (effectGroupValue = effectGroup.value()) != null
				&& !effectGroupValue.trim().isEmpty()) {
			return effectGroupValue;
		}

		// TODO: localizable
		return "No Group";
	}

}
