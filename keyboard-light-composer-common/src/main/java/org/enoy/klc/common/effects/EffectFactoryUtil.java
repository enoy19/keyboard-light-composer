package org.enoy.klc.common.effects;

import java.util.HashMap;
import java.util.Map;

import org.enoy.klc.common.effects.describers.EffectGroup;
import org.enoy.klc.common.effects.describers.EffectName;

public class EffectFactoryUtil {

	private static final Map<String, EffectFactory> FACTORIES = new HashMap<>();

	public static EffectFactory getEffectFactory(Class<? extends Effect> effectClass) {

		String effectClassName = effectClass.getName();

		if (!FACTORIES.containsKey(effectClassName)) {
			createEffectFactory(effectClass, effectClassName);
		}

		return FACTORIES.get(effectClassName);

	}

	private static void createEffectFactory(Class<? extends Effect> effectClass, String effectClassName) {
		String name = getEffectName(effectClass);
		String group = getEffectGroup(effectClass);
		EffectFactory factory = new EffectFactory(name, group, effectClass);

		FACTORIES.put(effectClassName, factory);
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
