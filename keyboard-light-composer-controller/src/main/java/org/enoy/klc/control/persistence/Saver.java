package org.enoy.klc.control.persistence;

import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.common.updatables.UpdatableRegister;

import java.io.*;

public class Saver {

	public static void save(LayerBase root, File saveFile) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
			oos.writeObject(root);
			oos.writeObject(UpdatableRegister.getInstance().getRegisteredObjectsAsList());
			oos.flush();
		}
	}

}
