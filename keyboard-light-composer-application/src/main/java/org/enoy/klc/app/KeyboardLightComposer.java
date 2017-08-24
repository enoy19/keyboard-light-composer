package org.enoy.klc.app;

import javafx.application.Application;
import org.enoy.klc.common.device.Device;
import org.enoy.klc.common.device.DeviceRegister;
import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.effects.EffectFactory;
import org.enoy.klc.common.effects.EffectsRegister;
import org.enoy.klc.common.effects.lights.blendmodes.BlendMode;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeFactory;
import org.enoy.klc.common.effects.lights.blendmodes.BlendModeRegister;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleMode;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleModeFactory;
import org.enoy.klc.common.effects.lights.scalemodes.ScaleModeRegister;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategy;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategyFactory;
import org.enoy.klc.common.properties.valuestrategy.ValueStrategyRegister;
import org.enoy.klc.control.PluginClassLoader;
import org.enoy.klc.control.exceptions.PluginLoadingException;
import org.enoy.klc.control.registerer.Registerer;

public class KeyboardLightComposer {

	public static void main(String[] args) throws ReflectiveOperationException {

		loadPlugins();

		// IntelliJ is not able to comprehend this much Lambda? (as it was before)
		Registerer.registerObjects(Device.class, DeviceRegister.getInstance());
		Registerer.registerParsed(Effect.class, c -> EffectFactory.createFactory(c), EffectsRegister.getInstance());
		Registerer.registerParsed(ValueStrategy.class, c -> ValueStrategyFactory.createFactory(c), ValueStrategyRegister.getInstance());
		Registerer.registerParsed(BlendMode.class, c -> BlendModeFactory.createFactory(c), BlendModeRegister.getInstance());
		Registerer.registerParsed(ScaleMode.class, c -> ScaleModeFactory.createFactory(c), ScaleModeRegister.getInstance());

		Application.launch(KeyboardLightComposerApplication.class, args);

		// DefaultRenderer renderer = new DefaultRenderer();
		// Device device =
		// DeviceRegister.getInstance().getRegisteredObjectsAsList().get(0);
		//
		// renderer.setDevice(device);
		//
		// Effect effect =
		// EffectsRegister.getInstance().getRegisteredObjectsAsList().get(0).newInstance();
		// EffectLayer solidColorLayer = new EffectLayer(effect);
		//
		// solidColorLayer.getEffectLayerInformation().getWidth().setValue(5);
		// solidColorLayer.getEffectLayerInformation().getHeight().setValue(3);
		//
		// solidColorLayer.getEffectLayerInformation().getX().setValue(3);
		// solidColorLayer.getEffectLayerInformation().getY().setValue(3);
		//
		// device.init();
		//
		// renderer.render(Arrays.asList(solidColorLayer));
		//
		// sleep();
		//
		// device.shutdown();

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
