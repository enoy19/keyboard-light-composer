package org.enoy.klc.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.enoy.klc.app.components.KlcApplication;
import org.enoy.klc.app.components.PropertyValueEditor;
import org.enoy.klc.app.components.property.editors.PropertyValueEditorFactory;
import org.enoy.klc.app.components.property.editors.PropertyValueEditorRegister;
import org.enoy.klc.common.updatables.UpdatableRegister;
import org.enoy.klc.control.DefaultRenderer;
import org.enoy.klc.control.effects.LayerRenderer;
import org.enoy.klc.control.external.ExternalServer;
import org.enoy.klc.control.registerer.Registerer;
import org.enoy.klc.control.updater.Updater;

import java.net.URL;
import java.util.ResourceBundle;

public class KeyboardLightComposerApplication extends Application {

	private Updater updater;

	private LayerRenderer layerRenderer;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Thread.currentThread().setUncaughtExceptionHandler(this::showJavaFxException);
		registerPropertyValueEditors();
		
		updater = new Updater();
		updater.setUpdatableRegister(UpdatableRegister.getInstance());
		Thread updateThread = new Thread(updater, "Update Thread");
		updateThread.setDaemon(true);
		updateThread.start();

		layerRenderer = new DefaultRenderer();
		Thread renderThread = new Thread(layerRenderer, "Render Thread");
		renderThread.setDaemon(true);
		renderThread.start();

		ResourceBundle resources = ResourceBundle.getBundle("fxml/i18n/klc");
		URL location = this.getClass().getResource("/fxml/KlcApplication.fxml");
		FXMLLoader loader = new FXMLLoader(location, resources);
		loader.load();

		KlcApplication controller = loader.getController();
		controller.setUpdater(updater);
		controller.setRenderer(layerRenderer);
		Scene scene = new Scene(loader.getRoot());

		// Start Server
        ExternalServer.startExternalServer();

		scene.getStylesheets().add("fxml/css/style.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Keyboard Light Composer");
		primaryStage.show();
	}

	private void showJavaFxException(Thread t, Throwable e) {
		System.err.println("!!!!! Uncaught exception in JavaFx Thread !!!!!");
		e.printStackTrace();
	}

	private void registerPropertyValueEditors() {
		Registerer.registerParsed(PropertyValueEditor.class,
				PropertyValueEditorFactory::createPropertyValueEditorFactory,
				PropertyValueEditorRegister.getInstance());
	}

}
