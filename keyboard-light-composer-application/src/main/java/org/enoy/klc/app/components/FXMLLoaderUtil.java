package org.enoy.klc.app.components;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

class FXMLLoaderUtil {

	static void loadRootControllerFXMLDocument(Object rootAndController,
			String resourceLocation, String resourceBundleBaseName) {
		loadRootControllerFXMLDocument(rootAndController, resourceLocation,
				ResourceBundle.getBundle(resourceBundleBaseName));
	}

	static void loadRootControllerFXMLDocument(Object rootAndController,
			String resourceLocation, ResourceBundle resources) {

		URL location = rootAndController.getClass()
				.getResource(resourceLocation);

		FXMLLoader loader = new FXMLLoader(location, resources);
		loader.setRoot(rootAndController);
		loader.setController(rootAndController);

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
