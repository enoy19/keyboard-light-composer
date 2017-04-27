package org.enoy.klc.control;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.enoy.klc.control.exceptions.PluginLoadingException;

public class PluginClassLoader {

	private static final File PLUGIN_DIR = new File("./plugins");

	public static final void setupPluginClassLoader() throws PluginLoadingException {
		Thread currentThread = Thread.currentThread();
		ClassLoader contextClassLoader = currentThread.getContextClassLoader();
		currentThread.setContextClassLoader(getPluginClassLoader(contextClassLoader));
	}

	public static final ClassLoader getPluginClassLoader(ClassLoader parent)
			throws PluginLoadingException {

		createPluginDirectoryIfNotExists();

		URL pluginUrl;
		try {
			pluginUrl = PLUGIN_DIR.toURI().toURL();
			return new URLClassLoader(new URL[]{pluginUrl}, parent);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new PluginLoadingException("failed to parse file to url", e);
			// TODO: logging
		}

	}

	private static void createPluginDirectoryIfNotExists()
			throws PluginLoadingException {
		if (!PLUGIN_DIR.exists()) {
			if (!PLUGIN_DIR.mkdirs()) {
				throw new PluginLoadingException(
						"Failed to create plugin directory");
			}
		}
	}

}
