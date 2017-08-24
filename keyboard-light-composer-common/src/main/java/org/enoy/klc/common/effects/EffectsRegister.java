package org.enoy.klc.common.effects;

import org.enoy.klc.common.Register;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EffectsRegister extends Register<EffectFactory> {

	private static EffectsRegister instance;

	private EffectsRegister() {

	}

	public Map<String, List<EffectFactory>> getEffectFactoryMap() {
		Map<String, List<EffectFactory>> map = new LinkedHashMap<>();
		getRegisteredStream()//
				.sorted((o1, o2) -> o1.getGroup().compareTo(o2.getGroup()))//
				.sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))//
				.forEach(ef -> addEffectFactoryToGroup(map, ef));

		return map;
	}

	private void addEffectFactoryToGroup(Map<String, List<EffectFactory>> map, EffectFactory ef) {
		String groupName = ef.getGroup();
		List<EffectFactory> group = map.get(groupName);

		if (group == null) {
			map.put(groupName, group = new ArrayList<>());
		}

		group.add(ef);
	}

	public static EffectsRegister getInstance() {
		return instance == null ? instance = new EffectsRegister() : instance;
	}

}
