package org.enoy.klc.app.components.property.editors;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.enoy.klc.app.components.PropertyValueEditor;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public abstract class NumberPropertyValueEditor<T extends Number>
		extends
			PropertyValueEditor<T> {

	private static final NumberFormat NUMBER_FORMAT = NumberFormat
			.getInstance(Locale.ENGLISH);
	private TextField textField;

	public NumberPropertyValueEditor() {
		textField.setOnAction(e -> {
			try {
				String value = textField.getText();
				Number number = NUMBER_FORMAT.parse(value);
				if (!value.equalsIgnoreCase(number.toString())) {
					textField.setText(number.toString());
				}
			} catch (NumberFormatException | ParseException ex) {
				textField.setText(
						NUMBER_FORMAT.format(getProperty().getValue()));
			}
			updateValue();
		});
	}

	@Override
	public void initEditorValue(T value) {
		String numberString = NumberFormat.getInstance().format(value);
		textField.setText(numberString);
	}

	@Override
	protected Node initContent() {
		textField = new TextField();
		return textField;
	}

	protected Number getTextFieldNumber() {
		try {
			return NumberFormat.getInstance().parse(textField.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
