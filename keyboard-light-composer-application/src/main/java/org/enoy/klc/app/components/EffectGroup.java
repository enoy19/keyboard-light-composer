package org.enoy.klc.app.components;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import org.enoy.klc.app.components.list.EffectFactoryListCell;
import org.enoy.klc.common.effects.EffectFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EffectGroup extends TitledPane implements Initializable {

    @FXML
    private ListView<EffectFactory> listEffects;
    
    public EffectGroup(String group, List<EffectFactory> effects) {
    	FXMLLoaderUtil.loadRootControllerFXMLDocument(this, "/fxml/EffectGroup.fxml", "fxml/i18n/klc");
    	setupEffectGroup(group, effects);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listEffects.setCellFactory(list -> new EffectFactoryListCell());
	}
	
	public void setupEffectGroup(String group, List<EffectFactory> effects) {
		setText(group);
    	listEffects.getItems().setAll(effects);
	}

}
