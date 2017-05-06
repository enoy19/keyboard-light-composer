package org.enoy.klc.control.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.common.updatables.UpdatableRegister;

public class Saver {

	public static void save(LayerBase root, File saveFile) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
			oos.writeObject(root);
			oos.writeObject(UpdatableRegister.getInstance().getRegisteredObjectsAsList());
			oos.flush();
		}
	}

}
