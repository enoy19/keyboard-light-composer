package org.enoy.klc.app;

import java.net.URL;
import java.util.ResourceBundle;

import org.enoy.klc.app.components.PropertyValueEditor;
import org.enoy.klc.app.components.PropertyValueEditor.PropertyValueEditorTypeClass;
import org.enoy.klc.app.components.property.editors.PropertyValueEditorFactory;
import org.enoy.klc.app.components.property.editors.PropertyValueEditorRegister;
import org.enoy.klc.control.registerer.Registerer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KeyboardLightComposerApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Thread.currentThread()
				.setUncaughtExceptionHandler(this::showJavaFxException);
		registerPropertyValueEditors();

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

	private void registerPropertyValueEditors() {
		Registerer.registerParsed(PropertyValueEditor.class, pveClass -> {
			Class<? extends PropertyValueEditor<?>> clazz = (Class<? extends PropertyValueEditor<?>>) pveClass;
			PropertyValueEditorTypeClass typeClass = clazz
					.getAnnotation(PropertyValueEditorTypeClass.class);
			if (typeClass == null) {
				throw new RuntimeException("property value editors must have a "
						+ PropertyValueEditorTypeClass.class.getName()
						+ " annotation with the edit type: "
						+ pveClass.getName());
			}
			Class<?> typeClassValue = typeClass.value();
			PropertyValueEditorFactory factory = new PropertyValueEditorFactory(
					typeClassValue, clazz);
			return factory;
		}, PropertyValueEditorRegister.getInstance());
	}

}
