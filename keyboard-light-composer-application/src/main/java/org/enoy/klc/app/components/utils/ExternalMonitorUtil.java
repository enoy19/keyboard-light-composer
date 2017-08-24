package org.enoy.klc.app.components.utils;

import org.enoy.klc.control.external.ExternalServer;
import org.enoy.klc.control.external.ExternalValue;
import org.enoy.klc.control.external.ExternalValueContainer;
import org.enoy.klc.control.external.ExternalValueNamespace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExternalMonitorUtil {

	public static List<ExternalMonitorObject> getObjects() {

		Map<ExternalValueNamespace, Set<ExternalValue<?>>> values = ExternalValueContainer.getInstance().getAll();
		List<ExternalMonitorObject> objects = new ArrayList<>(values.size());

		values.forEach((ns, s)->{
			final String scope = ns.getScope();
			final String identifier = ns.getIdentifier();

			s.forEach(v->{
				ExternalMonitorObject emo = new ExternalMonitorObject();

				emo.setScope(scope);
				emo.setIdentifier(identifier);
				emo.setParameter(v.getParameter());
				emo.setType(v.getDataType().getDataTypeId());
				emo.setData(v.getData().toString());
				objects.add(emo);
			});
		});

		return objects;

	}

}
