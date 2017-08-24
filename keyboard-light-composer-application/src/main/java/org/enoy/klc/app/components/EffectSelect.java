package org.enoy.klc.app.components;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.enoy.klc.app.components.list.EffectFactoryListCell;
import org.enoy.klc.common.effects.EffectFactory;
import org.enoy.klc.common.effects.EffectsRegister;

import java.net.URL;
import java.util.ResourceBundle;

public class EffectSelect implements Initializable {

	private static final EffectsRegister EFFECTS_REGISTER = EffectsRegister.getInstance();

	@FXML
	private TextField textFieldSearch;

	@FXML
	private ListView<EffectFactory> listSearch;

	@FXML
	private Accordion accordionGroups;

	private ObservableList<EffectFactory> effects;

	private FilteredList<EffectFactory> filteredEffects;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		effects = FXCollections.observableArrayList();
		filteredEffects = new FilteredList<>(effects);

		listSearch.visibleProperty().bind(textFieldSearch.textProperty().isNotEmpty());
		accordionGroups.visibleProperty().bind(textFieldSearch.textProperty().isEmpty());
		listSearch.setItems(filteredEffects);
		listSearch.setCellFactory(list -> new EffectFactoryListCell());

		textFieldSearch.textProperty().addListener((v, o, n) -> search(n));
		
		setupEffectList();
	}

	private void setupEffectList() {
		effects.clear();
		accordionGroups.getPanes().clear();

		EFFECTS_REGISTER.getRegisteredObjects().forEach(e -> {
			effects.add(e);
		});

		EFFECTS_REGISTER.getEffectFactoryMap().forEach((group, effects) -> {
			EffectGroup effectGroup = new EffectGroup(group, effects);
			accordionGroups.getPanes().add(effectGroup);
		});

		expandFirst();

	}

	private void expandFirst() {
		if (accordionGroups.getPanes().size() > 0) {
			Platform.runLater(() -> accordionGroups.getPanes().get(0).setExpanded(true));
		}
	}

	private void search(final String search) {
		filteredEffects.setPredicate(e -> e.getName().toLowerCase().contains(search.toLowerCase()));
	}

}