package org.enoy.klc.app.components.list;

import javafx.scene.control.ListCell;
import org.enoy.klc.common.device.Device;

public class DeviceListCell extends ListCell<Device> {

	@Override
	protected void updateItem(Device item, boolean empty) {
		super.updateItem(item, empty);
		if(!empty && item != null){
			setText(item.getName());
		}else{
			setText(null);
		}
	}
	
}
