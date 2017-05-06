package org.enoy.klc.app.components.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.control.persistence.Loader;
import org.enoy.klc.control.persistence.Saver;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class LoadSaveFileChooser {

	private static final String EXTENSION = "klc";
	private FileChooser fileChooser;

	public LoadSaveFileChooser() {
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Keyboard-Light-Composer File", "*." + EXTENSION));
	}

	public boolean save(Window ownerWindow, LayerBase root) throws FileNotFoundException, IOException {
		
		File saveFile = fileChooser.showSaveDialog(ownerWindow);
	
		if(saveFile != null){
			Saver.save(root, saveFile);
			return true;
		}else{
			return false;
		}
		
	}
	
	public LayerBase open(Window ownerWindow) throws FileNotFoundException, ClassNotFoundException, IOException{
		
		File openFile = fileChooser.showOpenDialog(ownerWindow);
		
		if(openFile != null){
			LayerBase layerBase = Loader.load(openFile);
			return layerBase;
		}
		
		return null;
		
	}

}
