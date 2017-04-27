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
		Device device = DeviceRegister.getInstance().getRegisteredObjectsAsList().get(0);

		renderer.setDevice(device);

		Effect effect = EffectsRegister.getInstance().getRegisteredObjectsAsList().get(0).newInstance();
		EffectLayer solidColorLayer = new EffectLayer(effect);

		solidColorLayer.getEffectLayerInformation().getWidth().setValue(5);
		solidColorLayer.getEffectLayerInformation().getHeight().setValue(3);
		
		solidColorLayer.getEffectLayerInformation().getX().setValue(3);
		solidColorLayer.getEffectLayerInformation().getY().setValue(3);

		device.init();
		
		renderer.render(Arrays.asList(solidColorLayer));
		
		sleep();
		
		device.shutdown();

	}

	private static void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
