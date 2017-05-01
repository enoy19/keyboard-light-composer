package org.enoy.klc.app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KeyboardLightComposerApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Thread.currentThread().setUncaughtExceptionHandler(this::showJavaFxException);

		ResourceBundle resources = ResourceBundle.getBundle("fxml/i18n/klc");
		URL location = this.getClass().getResource("/fxml/KlcApplication.fxml");
		FXMLLoader loader = new FXMLLoader(location, resources);
		loader.load();
		Scene scene = new Scene(loader.getRoot());

		scene.getStylesheets().add("fxml/css/style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showJavaFxException(Thread t, Throwable e) {
		System.err.println("!!!!! Uncaught exception in JavaFx Thread !!!!!");
		e.printStackTrace();
	}

}
