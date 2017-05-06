package org.enoy.klc.control;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.enoy.klc.control.exceptions.PluginLoadingException;

public class PluginClassLoader {

	private static final File PLUGIN_DIR = new File("plugins");

	public static final void setupPluginClassLoader() throws PluginLoadingException {
		Thread currentThread = Thread.currentThread();
		ClassLoader contextClassLoader = currentThread.getContextClassLoader();
		ClassLoader pluginClassLoader = getPluginClassLoader(contextClassLoader);
		
		currentThread.setContextClassLoader(pluginClassLoader);
	}

	private static final ClassLoader getPluginClassLoader(ClassLoader parent) throws PluginLoadingException {

		createPluginDirectoryIfNotExists();

		URL[] jarUrls;
		try {

			File[] jars = PLUGIN_DIR.listFiles((dir, name) -> {
				return dir.equals(PLUGIN_DIR) && name.endsWith(".jar");
			});

			jarUrls = new URL[jars.length];

			for (int i = 0; i < jars.length; i++) {
				jarUrls[i] = jars[i].toURI().toURL();
			}

			if(jarUrls.length > 0){
				return new URLClassLoader(jarUrls, parent);
			}
			
			return parent;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new PluginLoadingException("could not load plugin", e);
			// TODO: logging
		}

	}

	private static void createPluginDirectoryIfNotExists() throws PluginLoadingException {
		if (!PLUGIN_DIR.exists()) {
			if (!PLUGIN_DIR.mkdirs()) {
				throw new PluginLoadingException("Failed to create plugin directory");
			}
		}
	}

}
