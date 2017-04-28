package org.enoy.klc.app.components;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.enoy.klc.app.model.EffectListItem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EffectSelect extends VBox implements Initializable {

	@FXML
	private TextField textFieldSearch;

	@FXML
	private ListView<EffectListItem> listSearch;

	@FXML
	private Accordion accordionGroups;

	public EffectSelect() {
		FXMLLoaderUtil.loadRootControllerFXMLDocument(this,
				"fxml/EffectSelect.fxml", "klc");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listSearch.visibleProperty().bind(textFieldSearch.textProperty().isNotEmpty());
		accordionGroups.visibleProperty().bind(textFieldSearch.textProperty().isEmpty());
		
	}
	
	public void setupEffectList(){
		
		
		
	}
	
}