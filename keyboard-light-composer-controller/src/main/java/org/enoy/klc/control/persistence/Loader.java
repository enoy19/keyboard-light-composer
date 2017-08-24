package org.enoy.klc.control.persistence;

import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.common.updatables.Updatable;
import org.enoy.klc.common.updatables.UpdatableRegister;

import java.io.*;
import java.util.List;

public class Loader {

	@SuppressWarnings("unchecked")
	public static LayerBase load(File loadFile) throws FileNotFoundException, IOException, ClassNotFoundException {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(loadFile))) {
			LayerBase layerBase = (LayerBase) ois.readObject();
			List<Updatable> list = (List<Updatable>) ois.readObject();
			UpdatableRegister.getInstance().clear();
			list.forEach(UpdatableRegister.getInstance()::register);
			return layerBase;
		}

	}

}
