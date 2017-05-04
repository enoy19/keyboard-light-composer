package org.enoy.klc.app.components.utils;

import java.util.Collection;

import org.enoy.klc.app.components.KlcPropertyContainerEditor;
import org.enoy.klc.common.properties.KlcPropertyContainer;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Window;

public class DialogUtil {

	public static void confirm(Window owner, String title, String headerText, String contentText, Runnable onConfirm) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initOwner(owner);
		alert.initModality(Modality.WINDOW_MODAL);

		alert.showAndWait().ifPresent(bt -> {
			if (bt == ButtonType.OK) {
				onConfirm.run();
			}
		});
	}

	public static void alert(Window owner, String title, String headerText, String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initOwner(owner);
		alert.initModality(Modality.WINDOW_MODAL);

		alert.showAndWait();
	}

	public static <T> T select(Window owner, String title, String headerText, String contentText,
			Collection<T> choices) {

		Dialog<T> dialog = new ChoiceDialog<T>(null, choices);

		dialog.initOwner(owner);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setContentText(contentText);

		return dialog.showAndWait().orElse(null);

	}

	public static void showPropertyContainerEditor(Window owner, KlcPropertyContainer propertyContainer, String title,
			String headerText, String contentText) {

		Alert alert = new Alert(AlertType.CONFIRMATION, contentText, ButtonType.OK);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.initOwner(owner);
		alert.initModality(Modality.WINDOW_MODAL);

		KlcPropertyContainerEditor editor = new KlcPropertyContainerEditor();
		editor.setPrefWidth(300);
		editor.setPrefHeight(200);
		editor.setPropertyContainer(propertyContainer);
		alert.getDialogPane().setContent(editor);

		alert.showAndWait();

	}

}
