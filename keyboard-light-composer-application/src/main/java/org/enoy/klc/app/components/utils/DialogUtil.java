package org.enoy.klc.app.components.utils;

import java.util.Collection;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;

public class DialogUtil {

	public static void confirm(String title, String headerText,
			String contentText, Runnable onConfirm) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initModality(Modality.WINDOW_MODAL);

		alert.showAndWait().ifPresent(bt -> {
			if (bt == ButtonType.OK) {
				onConfirm.run();
			}
		});
	}

	public static void alert(String title, String headerText,
			String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initModality(Modality.WINDOW_MODAL);

		alert.showAndWait();
	}

	public static <T> T select(String title, String headerText,
			String contentText, Collection<T> choices) {

		Dialog<T> dialog = new ChoiceDialog<T>(null, choices);
		
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setContentText(contentText);
		
		return dialog.showAndWait().orElse(null);
		
	}

}
