package org.enoy.klc.app.components.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

public class DialogUtil {
	
	public static void confirm(String title, String headerText, String contentText, Runnable onConfirm){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initModality(Modality.WINDOW_MODAL);
		
		alert.showAndWait().ifPresent(bt->{
			if(bt == ButtonType.OK){
				onConfirm.run();
			}
		});
	}

}
