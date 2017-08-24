package org.enoy.klc.common.effects;

import org.enoy.klc.common.Deletable;

import java.io.Serializable;

public interface Effect extends Renderable, Deletable, Serializable {
	
	public String getName();

	// TODO: author
	
}
