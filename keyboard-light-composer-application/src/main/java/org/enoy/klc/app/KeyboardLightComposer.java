package org.enoy.klc.app;

import java.util.Arrays;

import org.enoy.klc.common.device.Device;
import org.enoy.klc.common.device.DeviceRegister;
import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.effects.EffectsRegister;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.control.DefaultRenderer;
import org.enoy.klc.control.PluginClassLoader;
import org.enoy.klc.control.exceptions.PluginLoadingException;
import org.enoy.klc.control.registerer.Registerer;

public class KeyboardLightComposer {

	public static void main(String[] args) throws ReflectiveOperationException {

		loadPlugins();

		Registerer.registerObjects(Device.class, DeviceRegister.getInstance());
		Registerer.registerClasses(Effect.class, EffectsRegister.getInstance());

		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setDevice(DeviceRegister.getInstance()
				.getRegisteredObjectsAsList().get(0));

		EffectLayer solidColorLayer = new EffectLayer(
				EffectsRegister.getInstance().getRegisteredObjectsAsList()
						.get(0).newInstance());
		
		solidColorLayer.getEffectLayerInformation().getWidth().setValue(21);
		solidColorLayer.getEffectLayerInformation().getHeight().setValue(6);
		solidColorLayer.getEffectLayerInformation().getScaleY().setValue(1f);
		solidColorLayer.getEffectLayerInformation().getScaleY().setValue(0.9f);
	
		renderer.render(Arrays.asList(solidColorLayer));

	}

	private static void loadPlugins() {
		try {
			PluginClassLoader.setupPluginClassLoader();
		} catch (PluginLoadingException e) {
			e.printStackTrace();
			System.err.println("Failed to load plugins");
		}
	}

}
