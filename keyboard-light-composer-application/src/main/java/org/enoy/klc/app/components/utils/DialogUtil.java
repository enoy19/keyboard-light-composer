package org.enoy.klc.app.components.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Window;
import org.enoy.klc.app.components.KlcPropertyContainerEditor;
import org.enoy.klc.common.properties.KlcPropertyContainer;

import java.util.Collection;

public class DialogUtil {

	public static void confirm(Window owner, String title, String headerText, String contentText, Runnable onConfirm) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		setupDialog(owner, title, headerText, contentText, alert);

		alert.showAndWait().ifPresent(bt -> {
			if (bt == ButtonType.OK) {
				onConfirm.run();
			}
		});
	}

	public static void error(Window owner, String title, String headerText, String contentText) {

		Alert alert = new Alert(AlertType.ERROR);
		setupDialog(owner, title, headerText, contentText, alert);
		alert.showAndWait();

	}

	private static void setupDialog(Window owner, String title, String headerText, String contentText, Dialog<?> alert) {
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initOwner(owner);
		alert.initModality(Modality.WINDOW_MODAL);
	}

	public static void alert(Window owner, String title, String headerText, String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		setupDialog(owner, title, headerText, contentText, alert);

		alert.showAndWait();
	}

	public static <T> T select(Window owner, String title, String headerText, String contentText,
			Collection<T> choices) {

		Dialog<T> dialog = new ChoiceDialog<T>(null, choices);

		setupDialog(owner, title, headerText, contentText, dialog);

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
