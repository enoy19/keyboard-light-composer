package org.enoy.klc.app.components.list;

import org.enoy.klc.common.effects.EffectFactory;

import javafx.scene.control.ListCell;

public class EffectFactoryListCell extends ListCell<EffectFactory> {

	@Override
	protected void updateItem(EffectFactory item, boolean empty) {
		super.updateItem(item, empty);
		if(!empty && item != null){
			setText(item.getName());
		}else{
			setText(null);
		}
	}

}
